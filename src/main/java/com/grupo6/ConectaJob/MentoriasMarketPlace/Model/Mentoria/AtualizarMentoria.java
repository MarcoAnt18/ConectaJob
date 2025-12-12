package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentoria;

import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.StrategyAtualizarAnuncio;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import org.springframework.stereotype.Component;

@Component("AtualizarMentoria")
public class AtualizarMentoria implements StrategyAtualizarAnuncio {
    public void atualizar(Anuncio anuncioParaAtualizar, Anuncio novoAnuncio){
        Mentoria mentoriaParaAtualizar = (Mentoria) anuncioParaAtualizar;

        mentoriaParaAtualizar.atualizarAnuncio(novoAnuncio);
    }
}
