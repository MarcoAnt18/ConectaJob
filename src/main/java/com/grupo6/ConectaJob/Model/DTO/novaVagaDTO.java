package com.grupo6.ConectaJob.Model.DTO;

import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempo;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempoSalvosubCategorias;
import com.grupo6.ConectaJob.Model.cargo.Cargo;
import com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado;

public record novaVagaDTO(String empresaReponsavelCNPJ, servicoPrestado servicoPrestadoNaOcasiao,
                          Cargo cargo, IntervaloTempo jornadaAmpla, IntervaloTempoSalvosubCategorias jornandaDetalhada,
                          Integer numeroVagas, String pagamento, String meioDeComunicacao, String equipamentoDeSeguranca) {
}