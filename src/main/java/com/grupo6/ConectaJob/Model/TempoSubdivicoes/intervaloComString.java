package com.grupo6.ConectaJob.Model.TempoSubdivicoes;

public class intervaloComString {
    private intervaloTempo intervaloTempoX;
    private String complemento;

    public intervaloComString() {
    }

    public intervaloTempo getIntervaloTempoX() {
        return intervaloTempoX;
    }

    public void setIntervaloTempoX(intervaloTempo intervaloTempoX) {
        this.intervaloTempoX = intervaloTempoX;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
