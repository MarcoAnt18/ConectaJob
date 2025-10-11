package com.grupo6.ConectaJob.Model;

import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class userTrabalhador extends userGeneric {
    private List<avaliacao> avaliacoes;
    private com.grupo6.ConectaJob.Model.listaAvaliacoesSegundoCargo listaAvaliacoesSegundoCargo;

    public List<avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

}
