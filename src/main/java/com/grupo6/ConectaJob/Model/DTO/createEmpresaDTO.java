package com.grupo6.ConectaJob.Model.DTO;

import com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado;

import java.util.List;

public record createEmpresaDTO(String CPFatrelado, String CNPJ,String nomeEmpresa, String segmento, List<servicoPrestado> servicoPrestadoList) {
}
