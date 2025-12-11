package com.grupo6.ConectaJob.Model.userGeneric;

import com.grupo6.ConectaJob.Model.DTO.RetornoTrabalhadorDTO;
import com.grupo6.ConectaJob.Model.DTO.retornoUsuarioDTO;
import com.grupo6.ConectaJob.Model.userTrabalhador;

public class CriarRetornoUsuarioDTO implements StrategyRetornoUsuarioDTO {

    @Override
    public retornoUsuarioDTO criarretornoUsuario(Usuario usuario) {
        userTrabalhador funcionario = (userTrabalhador) usuario;

        return new RetornoTrabalhadorDTO(
                funcionario.getCEP(),
                funcionario.getCEP(),
                funcionario.getFormacao(),
                funcionario.getNome(),
                funcionario.getFtperfilLink(),
                funcionario.getDtNascimento()
        );
    }
}
