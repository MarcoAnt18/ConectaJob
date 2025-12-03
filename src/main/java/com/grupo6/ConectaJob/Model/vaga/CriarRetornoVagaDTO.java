package com.grupo6.ConectaJob.Model.vaga;

import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.StrategyRetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoVagaDTO;

public class CriarRetornoVagaDTO implements StrategyRetornoAnuncioDTO {

    public RetornoAnuncioDTO criarRetornoAnuncioDTO(Anuncio anuncio){
        VagaTrabalho vaga = (VagaTrabalho) anuncio;

        return new RetornoVagaDTO(
                vaga.getAnuncianteResponsavelId(),
                vaga.getNomeAnuncio(),
                vaga.getDescricaoAnuncio(),
                vaga.getMeioDeComunicacao(),
                vaga.getPagamento(),
                vaga.getQuantidade(),
                vaga.getCargo(),
                vaga.getEquipamentoDeSeguranca(),
                vaga.getJornadaAmpla(),
                vaga.getJornandaDetalhada()
        );
    }
}
