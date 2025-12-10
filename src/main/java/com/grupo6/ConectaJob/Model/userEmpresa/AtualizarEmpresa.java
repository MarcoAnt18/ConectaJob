package com.grupo6.ConectaJob.Model.userEmpresa;

import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.Anunciante.StrategyAtualizarAnunciante;

public class AtualizarEmpresa implements StrategyAtualizarAnunciante {

    public void atualizar(Anunciante anuncianteParaAtualziar, Anunciante novoAnunciante){
        Empresa empresaParaAtualizada = (Empresa) anuncianteParaAtualziar;
        empresaParaAtualizada.atualizarAnunciante(novoAnunciante);
    }

}