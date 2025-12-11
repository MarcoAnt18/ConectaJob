package com.grupo6.ConectaJob.Model.userGeneric;

import com.grupo6.ConectaJob.Model.userTrabalhador;

public abstract class ValidarTrabalhador {

    public void validarTrabalhador(userTrabalhador trabalhador){

    }

    public void validarAtributosTrabalhador(){

    }
    public boolean entradaVazia(String entrada){
        if (entrada == null || entrada.isBlank()){
            return false;
        }
        return true;
    }

    public boolean entradaMaxima(String entrada, int numero){
        if (entrada.length() > numero){
            return false;
        }
        return true;
    }

    public void validarCEP(String CEP){

    }
}
