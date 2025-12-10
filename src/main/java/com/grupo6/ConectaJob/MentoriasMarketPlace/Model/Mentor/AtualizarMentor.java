package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentor;

import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.Anunciante.StrategyAtualizarAnunciante;
import com.grupo6.ConectaJob.Model.userEmpresa.Empresa;

public class AtualizarMentor implements StrategyAtualizarAnunciante {

    public void atualizar(Anunciante anuncianteParaAtualziar, Anunciante novoAnunciante){
        Mentor mentorParaAtualizar = (Mentor) anuncianteParaAtualziar;
        mentorParaAtualizar.atualizarAnunciante(novoAnunciante);
    }
}
