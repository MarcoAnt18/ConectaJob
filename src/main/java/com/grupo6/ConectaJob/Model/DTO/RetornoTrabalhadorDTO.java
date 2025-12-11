package com.grupo6.ConectaJob.Model.DTO;

import java.util.Date;

public class RetornoTrabalhadorDTO extends retornoUsuarioDTO{

    private String CEP;

    private String Email;

    private String formacao;

    public RetornoTrabalhadorDTO(){}

    public RetornoTrabalhadorDTO(String cep, String email, String Formacao, String nome, String ftLink, Date dtnasc){
        super(nome, ftLink, dtnasc);
        this.CEP = cep;
        this.Email = email;
        this.formacao = Formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public String getEmail() {
        return Email;
    }

    public String getCEP() {
        return CEP;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
