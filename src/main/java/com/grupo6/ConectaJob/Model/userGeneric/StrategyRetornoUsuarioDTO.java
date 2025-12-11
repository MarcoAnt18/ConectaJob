package com.grupo6.ConectaJob.Model.userGeneric;

import com.grupo6.ConectaJob.Model.DTO.retornoUsuarioDTO;

public interface StrategyRetornoUsuarioDTO {
    public retornoUsuarioDTO criarretornoUsuario(Usuario usuario);
}
