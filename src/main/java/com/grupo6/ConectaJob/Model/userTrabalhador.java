package com.grupo6.ConectaJob.Model;

import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userTrabalhador extends userGeneric {
    private com.grupo6.ConectaJob.Model.listaAvaliacoesSegundoCargo listaAvaliacoesSegundoCargo;
}
