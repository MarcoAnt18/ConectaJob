package com.grupo6.ConectaJob.Model;

import com.grupo6.ConectaJob.Model.userGeneric.Usuario;
import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userTrabalhador extends Usuario {
    //private com.grupo6.ConectaJob.Model.listaAvaliacoesSegundoCargo listaAvaliacoesSegundoCargo;

    private String Formacao;
    private String Email;
    private String CEP;

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public void setFormacao(String formacao) {
        Formacao = formacao;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCEP() {
        return CEP;
    }

    public String getEmail() {
        return Email;
    }

    public String getFormacao() {
        return Formacao;
    }

    public
}
