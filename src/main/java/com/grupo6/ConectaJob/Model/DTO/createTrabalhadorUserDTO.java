package com.grupo6.ConectaJob.Model.DTO;

import com.grupo6.ConectaJob.Model.avaliacao;

import java.util.List;

public record createTrabalhadorUserDTO (String nome, String cpf, String senha, List<avaliacao> avaliacoes) {
}
