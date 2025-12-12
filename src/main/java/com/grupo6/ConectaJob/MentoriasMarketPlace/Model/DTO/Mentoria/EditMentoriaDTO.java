package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.DTO.Mentoria;

import com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentoria.Mentoria;
import com.grupo6.ConectaJob.Model.DTO.SearchAnuncioDTO;

public record EditMentoriaDTO(SearchAnuncioDTO searchAnuncioDTO, Mentoria novaMentoria) {
}
