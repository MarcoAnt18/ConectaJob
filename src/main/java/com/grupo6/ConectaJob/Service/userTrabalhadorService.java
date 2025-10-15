package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.buscarTrabalhadorDTO;
import com.grupo6.ConectaJob.Model.DTO.createTrabalhadorUserDTO;
import com.grupo6.ConectaJob.Model.DTO.loginTrabalhadorDTO;
import com.grupo6.ConectaJob.Model.DTO.returnTrabalhadorDTO;
import com.grupo6.ConectaJob.Model.vaga.userTrabalhadorRepository;
import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import com.grupo6.ConectaJob.Model.userTrabalhador;
import org.springframework.beans.factory.annotation.Autowired;

public class userTrabalhadorService {
    private userTrabalhadorRepository userTrabalhador;
    public userGeneric buscartrabalhador(buscarTrabalhadorDTO trabalhadorDTO){
        var trabalhador = userTrabalhador.finduserTrabalhadorbyCPF(trabalhadorDTO.cpf());

        if (trabalhador == null){
            throw new notFound("trabalhador não encontrado");
        }

        return new returnTrabalhadorDTO(trabalhador.getUsername(), trabalhador.getPassword() trabalhador.)

    }

    public boolean deletetrabalhador (loginTrabalhadorDTO trabalhadordto, String cpf){
        var cpfusuario = userTrabalhador.finduserTrabalhadorbyCPF(cpf);

        if (cpfusuario == null){
            throw new notFound("usuario nao encontrado");
        }
        userTrabalhador.delete(cpfusuario);
        return true;
    }

    public boolean createtrabalhador(createTrabalhadorUserDTO trabalhadoruserDTO){
        var trabalhadoruser = userTrabalhador.finduserTrabalhadorbyCPF(trabalhadoruserDTO.cpf());
        if (trabalhadoruser == null){
            throw new notFound("usuario nao encontrado");
        }

        trabalhadoruser = new userTrabalhador(trabalhadoruserDTO.cpf(), trabalhadoruserDTO.nome(), trabalhadoruserDTO.senha());
        userTrabalhador.save(trabalhadoruser);
        return true;
    }

    public userTrabalhador buscaruserTrabalhador(){
        var trabalhadoruser = userTrabalhador.finduserTrabalhadorbyCPF(buscar);
    }

}
