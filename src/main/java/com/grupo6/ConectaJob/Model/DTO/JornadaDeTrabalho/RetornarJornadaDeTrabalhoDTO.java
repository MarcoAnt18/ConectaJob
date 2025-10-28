package com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho;

import com.grupo6.ConectaJob.Model.RegistroPonto.RegistroPonto;

import java.util.List;

public record RetornarJornadaDeTrabalhoDTO(List<RegistroPonto> jornadaDeTrabalho) {
}
