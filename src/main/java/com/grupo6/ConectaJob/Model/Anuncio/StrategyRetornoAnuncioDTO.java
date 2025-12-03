package com.grupo6.ConectaJob.Model.Anuncio;

import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoAnuncioDTO;

public interface StrategyRetornoAnuncioDTO {

    public RetornoAnuncioDTO criarRetornoAnuncioDTO(Anuncio anuncio);
}
