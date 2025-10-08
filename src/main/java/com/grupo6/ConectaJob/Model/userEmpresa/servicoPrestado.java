package com.grupo6.ConectaJob.Model.userEmpresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class servicoPrestado {
    private String nomeServico;
    private String descricaoDoServico;

    public servicoPrestado() {}

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDescricaoDoServico() {
        return descricaoDoServico;
    }

    public void setDescricaoDoServico(String descricaoDoServico) {
        this.descricaoDoServico = descricaoDoServico;
    }

    @Override
    public String toString() {
        return "servicoPrestado{" +
                "nomeServico='" + nomeServico + '\'' +
                ", descricaoDoServico='" + descricaoDoServico + '\'' +
                '}';
    }
}
