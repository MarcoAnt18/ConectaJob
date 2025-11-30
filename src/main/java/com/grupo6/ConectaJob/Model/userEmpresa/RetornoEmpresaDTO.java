package com.grupo6.ConectaJob.Model.userEmpresa;

import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.Anunciante.StrategyRetornoAnuncianteDTO;
import com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoAnuncianteDTO;

public class RetornoEmpresaDTO implements StrategyRetornoAnuncianteDTO {

    public RetornoAnuncianteDTO CriarRetornoAnuncianteDTO(Anunciante anunciante){
        Empresa empresa = (Empresa) anunciante;

        return new com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoEmpresaDTO(
                empresa.getNomeAnunciante(),
                empresa.getMeioDeComunicacao(),
                empresa.getFtPerfilLink(),
                empresa.getCnpjEmpresa(),
                empresa.getSegmento(),
                empresa.getServicoPrestado(),
                empresa.getAvaliacoesSegundoCargo()
        );
    }
}
