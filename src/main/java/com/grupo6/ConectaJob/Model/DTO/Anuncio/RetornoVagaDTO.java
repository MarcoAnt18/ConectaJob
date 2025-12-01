package com.grupo6.ConectaJob.Model.DTO.Anuncio;

import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempo;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempoSalvosubCategorias;
import com.grupo6.ConectaJob.Model.cargo.Cargo;

public class RetornoVagaDTO extends RetornoAnuncioDTO{

    private Cargo cargo;
    private String equipamentoDeSeguranca;
    private IntervaloTempo jornadaAmpla;
    private IntervaloTempoSalvosubCategorias jornandaDetalhada;

    public RetornoVagaDTO() {}

    public RetornoVagaDTO(String anuncianteResponsavelId, String nomeAnuncio, String descricaoAnuncio,
                          String meioDeComunicacao, String pagamento, Integer quantidade, Cargo cargo,
                          String equipamentoDeSeguranca, IntervaloTempo jornadaAmpla,
                          IntervaloTempoSalvosubCategorias jornandaDetalhada)
    {
        super(anuncianteResponsavelId, nomeAnuncio, descricaoAnuncio, meioDeComunicacao, pagamento, quantidade);
        this.cargo = cargo;
        this.equipamentoDeSeguranca = equipamentoDeSeguranca;
        this.jornadaAmpla = jornadaAmpla;
        this.jornandaDetalhada = jornandaDetalhada;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getEquipamentoDeSeguranca() {
        return equipamentoDeSeguranca;
    }

    public void setEquipamentoDeSeguranca(String equipamentoDeSeguranca) {
        this.equipamentoDeSeguranca = equipamentoDeSeguranca;
    }

    public IntervaloTempo getJornadaAmpla() {
        return jornadaAmpla;
    }

    public void setJornadaAmpla(IntervaloTempo jornadaAmpla) {
        this.jornadaAmpla = jornadaAmpla;
    }

    public IntervaloTempoSalvosubCategorias getJornandaDetalhada() {
        return jornandaDetalhada;
    }

    public void setJornandaDetalhada(IntervaloTempoSalvosubCategorias jornandaDetalhada) {
        this.jornandaDetalhada = jornandaDetalhada;
    }
}
