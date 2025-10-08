package com.grupo6.ConectaJob.Model.DTO;

import com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado;

import java.util.List;

public record retornoEmpresaExiste(String CNPJ, String nomeEmpresa, String segmento, String ftPerfilLink, List<servicoPrestado> servicoPrestado) {
}
