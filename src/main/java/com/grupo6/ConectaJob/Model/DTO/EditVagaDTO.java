package com.grupo6.ConectaJob.Model.DTO;

import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;

public record EditVagaDTO(SearchAnuncioDTO searchAnuncioDTO, VagaTrabalho novaVaga) {
}