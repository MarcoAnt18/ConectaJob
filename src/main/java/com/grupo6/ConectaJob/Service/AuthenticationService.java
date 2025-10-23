package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ConfgSeguranca.tokenConfig;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.criarNotificacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.tokenDTO;
import com.grupo6.ConectaJob.Model.DTO.createTrabalhadorUserDTO;
import com.grupo6.ConectaJob.Model.DTO.loginTrabalhadorDTO;
import com.grupo6.ConectaJob.Model.notificacao.Notificacao;
import com.grupo6.ConectaJob.Model.userEmpresa.EmpresaRepository;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import com.grupo6.ConectaJob.Model.userTrabalhador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private tokenConfig tokenConfig;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserGenericRepository UserGenericRepository;
    //PARTE DO CRUD DE NOTIFICAÇÃO
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EmpresaService empresaService; //TAMBEM PARTE DA NOITFICACAO

    @PostMapping()
    public ResponseEntity loginTrabalhador (@RequestBody loginTrabalhadorDTO loginTrabalhadorDTO){

        var userModeloIdeal = new UsernamePasswordAuthenticationToken(loginTrabalhadorDTO.cpf(), loginTrabalhadorDTO.senha());
        var loginReference = authenticationManager.authenticate(userModeloIdeal);
        return ResponseEntity.ok(new tokenDTO(tokenConfig.TokenGenerate((userGeneric) loginReference.getPrincipal())));
    }
    @PostMapping("/createUser")
    public void createTrabalhadorUer(@RequestBody createTrabalhadorUserDTO createTrabalhadorUserDTO) {

        var newTrabalhador = new userTrabalhador();
        newTrabalhador.setNome(createTrabalhadorUserDTO.nome());
        newTrabalhador.setCpf(createTrabalhadorUserDTO.cpf());
        newTrabalhador.setSenha(passwordEncoder.encode(createTrabalhadorUserDTO.senha()));

        UserGenericRepository.save(newTrabalhador);
    }

    //O correto é o usuário também ter a variável de contato, adicionar no userGeneric depois.
    @PostMapping("/aplicarVaga")
    public boolean aplicarParaVaga(@RequestBody criarNotificacaoDTO criarNotificacaoDTO){

        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(criarNotificacaoDTO.empresaResponsavelCPNJ());

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

         var usuarioResponsavel = UserGenericRepository.findUserGenericByCpf(criarNotificacaoDTO.usuarioCPF());


        var vagaEscolhida = empresaService.buscarVagaTrabalho(criarNotificacaoDTO.nomeVaga(), criarNotificacaoDTO.empresaResponsavelCPNJ());

        if (vagaEscolhida == null){
            throw new notFound("Vaga com esse nome não encontrada na empresa");
        }

        Notificacao novaNotificacao = new Notificacao(usuarioResponsavel,
                                                      criarNotificacaoDTO.contato(),
                                                 "Aplicação para vaga",
                                                      vagaEscolhida);

        empresaResponsavel.setNotificacoes(novaNotificacao);

        empresaRepository.save(empresaResponsavel);

        return true;
    }
}
