package com.grupo6.ConectaJob.Model.DTO;

import com.grupo6.ConectaJob.Model.userEmpresa.Empresa;

public record editEmpresaDTO(searchDTO searchCNPJ, Empresa novaEmpresa) {
}