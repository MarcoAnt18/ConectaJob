package com.grupo6.ConectaJob.Model.cargo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class cargo {

    private String nomeCargo;
    private String visaoCargo;

    public cargo() {}

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public String getVisaoCargo() {
        return visaoCargo;
    }

    public void setVisaoCargo(String visaoCargo) {
        this.visaoCargo = visaoCargo;
    }
}
