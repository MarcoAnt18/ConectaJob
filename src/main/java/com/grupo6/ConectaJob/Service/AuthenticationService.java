package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ConfgSeguranca.tokenConfig;
import com.grupo6.ConectaJob.Model.DTO.tokenDTO;
import com.grupo6.ConectaJob.Model.DTO.createTrabalhadorUserDTO;
import com.grupo6.ConectaJob.Model.DTO.loginTrabalhadorDTO;
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


}
