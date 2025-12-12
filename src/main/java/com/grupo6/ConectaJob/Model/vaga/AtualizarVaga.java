package com.grupo6.ConectaJob.Model.vaga;

import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.StrategyAtualizarAnuncio;
import org.springframework.stereotype.Component;

@Component("AtualizarVaga")
public class AtualizarVaga implements StrategyAtualizarAnuncio {

    public void atualizar(Anuncio anuncioParaAtualizar, Anuncio novoAnuncio){
        VagaTrabalho vagaParaAtualizar = (VagaTrabalho) anuncioParaAtualizar;

        vagaParaAtualizar.atualizarAnuncio(novoAnuncio);
    }

}
