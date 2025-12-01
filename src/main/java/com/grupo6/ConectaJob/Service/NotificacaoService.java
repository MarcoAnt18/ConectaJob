package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.RetornoNotificacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.criarNotificacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.deletarNotifcacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.searchDTO;
import com.grupo6.ConectaJob.Model.notificacao.Notificacao;
import com.grupo6.ConectaJob.Model.notificacao.NotificacaoUsuarioInfo;
import com.grupo6.ConectaJob.Model.notificacao.NotificacaoVagaInfo;
import com.grupo6.ConectaJob.Model.userEmpresa.EmpresaRepository;
import com.grupo6.ConectaJob.Model.userEmpresa.Empresa;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NotificacaoService {

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    UserGenericRepository userGenericRepository;

    @Autowired
    AnuncioService anuncioService;

    //Criara Notificação
    public boolean aplicarParaVaga(criarNotificacaoDTO criarNotificacaoDTO){
        Empresa empresaResponsavel = buscarEmpresa(criarNotificacaoDTO.empresaResponsavelCPNJ());

        userGeneric usuarioResponsavel = buscarUsuario(criarNotificacaoDTO.usuarioCPF());

        VagaTrabalho vagaEscolhida = buscarVaga(
                criarNotificacaoDTO.nomeVaga(),
                criarNotificacaoDTO.empresaResponsavelCPNJ()
        );

        NotificacaoUsuarioInfo usuarioInfos = pegarUsuarioInfos(usuarioResponsavel);

        //NotificacaoVagaInfo vagaInfos = pegarVagaInfos(vagaEscolhida);
        NotificacaoVagaInfo vagaInfos = null; //Retirar depois, e deixar a comentado

        Notificacao novaNotificacao = new Notificacao(
                usuarioInfos,
                criarNotificacaoDTO.contato(),
                "Aplicação para vaga",
                vagaInfos
        );

        //empresaResponsavel.setNotificacoes(novaNotificacao);

        empresaRepository.save(empresaResponsavel);

        return true;
    }

    public boolean deletarNotificacao(deletarNotifcacaoDTO deletarNotifcacaoDTO){

        Empresa empresaResponsavel = buscarEmpresa(deletarNotifcacaoDTO.empresaCNPJ());

        List<Notificacao> notificacoes = empresaResponsavel.getNotificacoes();

        Notificacao notificacaoParaApagar = null;

        for(Notificacao notificacao : notificacoes){
            if(
                    Objects.equals(notificacao.getUsuarioInfos().getCpf(), deletarNotifcacaoDTO.usuarioCPF())
                    && Objects.equals(notificacao.getVagaTrabalhoInfos().getCargo().getNomeCargo(), deletarNotifcacaoDTO.nomeVaga())
            ){
                notificacaoParaApagar = notificacao;
                break;
            }
        }

        if(notificacaoParaApagar == null){
            throw new notFound("Notificação não encontrada");
        }

        //empresaResponsavel.deleteNotificacao(notificacaoParaApagar);

        empresaRepository.save(empresaResponsavel);

        return true;
    }

    public RetornoNotificacaoDTO buscarNotificacoes(searchDTO searchDTO){
        var empresaResponsavel = buscarEmpresa(searchDTO.id());

        if(empresaResponsavel.getNotificacoes().isEmpty()){
            throw new notFound("A empresa não possui notificações");
        }

        return new RetornoNotificacaoDTO(empresaResponsavel.getNotificacoes());
    }

    public Empresa buscarEmpresa(String empresaResponsavelCNPJ){
        Empresa empresaResponsavel = null;

        empresaResponsavel = empresaRepository.findEmpresaByCNPJ(empresaResponsavelCNPJ);

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ não encontrado");
        }

        return empresaResponsavel;
    }

    public userGeneric buscarUsuario(String usuarioCPF){
        userGeneric usuarioInformado = null;

        usuarioInformado = userGenericRepository.findUserGenericByCpf(usuarioCPF);

        if(usuarioInformado == null){
            throw new notFound("Usuário com este CPF não encontrado");
        }

        return usuarioInformado;
    }

    public VagaTrabalho buscarVaga(String nomeVaga, String empresaResposavelCNPJ){
        VagaTrabalho vagaInformada = null;

        vagaInformada = anuncioService.buscarAnuncioBD(nomeVaga, empresaResposavelCNPJ);

        if (vagaInformada == null){
            throw new notFound("Vaga com esse nome não encontrada na empresa");
        }

        return vagaInformada;
    }

    public NotificacaoUsuarioInfo pegarUsuarioInfos(userGeneric usuario){
       return new NotificacaoUsuarioInfo(
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getDtNascimento()
                );
    }

    /*public NotificacaoVagaInfo pegarVagaInfos(vagaTrabalho vaga){
        return new NotificacaoVagaInfo(
                vaga.getServicoPrestadoNaOcasiao(),
                vaga.getCargoIndividuo(),
                vaga.getMeioDeComunicacao(),
                vaga.getEquipamentoDeSeguranca(),
                vaga.getPagamento(),
                vaga.getJornadaAmpla(),
                vaga.getJornandaDetalhada()
        );
    }*/
}
