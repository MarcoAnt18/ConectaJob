package com.grupo6.ConectaJob.Model.DTO.ConferirVaga;


import java.util.List;

public record retornoConferirVaga(List<String> diferencasContratoEVaga, List<String> irregularidadesVaga) {
}
