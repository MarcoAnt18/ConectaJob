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

import java.util.Collections;

public class userTrabalhadorService {
    private userTrabalhadorRepository userTrabalhador;
    public boolean edittrabalhador (String cpf, createTrabalhadorUserDTO trabalhadorDTO){
        var trabAntigo = userTrabalhador.finduserTrabalhadorbyCPF(cpf);
        boolean verificador = false;

        if (trabAntigo == null){
            throw new notFound("o trabalhador nao existe");
        } else {
            var trabNovo = new userTrabalhador((trabalhadorDTO.cpf() == null)? trabAntigo.getCpf() : trabalhadorDTO.cpf(), (trabalhadorDTO.nome() == null)? trabAntigo.getNome() : trabalhadorDTO.nome(), (trabalhadorDTO.senha() == null)? trabAntigo.getSenha() : trabalhadorDTO.senha(), (trabalhadorDTO.avaliacoes() == null)? trabAntigo.getAvaliacoes() : trabalhadorDTO.avaliacoes());
            trabAntigo.setId(trabAntigo.getId());
            userTrabalhador.save(trabNovo);
            verificador = true;
        }
        return verificador;

    }

    public boolean deletetrabalhador (String cpf){
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

        trabalhadoruser = new userTrabalhador(trabalhadoruserDTO.cpf(), trabalhadoruserDTO.nome(), trabalhadoruserDTO.senha(), trabalhadoruserDTO.avaliacoes());
        userTrabalhador.save(trabalhadoruser);
        return true;
    }

    public createTrabalhadorUserDTO buscaruserTrabalhador(String cpf){
        var trabalhadoruser = userTrabalhador.finduserTrabalhadorbyCPF(cpf);

        if(trabalhadoruser == null){
            throw new notFound("o usuario nao existe");
        }

        System.out.println(trabalhadoruser);
        return new createTrabalhadorUserDTO(trabalhadoruser.getCpf(), trabalhadoruser.getNome(), trabalhadoruser.getSenha(), trabalhadoruser.getAvaliacoes());
    }

}
