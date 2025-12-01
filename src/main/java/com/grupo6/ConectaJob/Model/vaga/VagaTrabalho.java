package com.grupo6.ConectaJob.Model.vaga;

import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempo;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempoSalvosubCategorias;
import com.grupo6.ConectaJob.Model.cargo.Cargo;

public class VagaTrabalho extends Anuncio{

    private Cargo cargo;
    private String equipamentoDeSeguranca;
    private IntervaloTempo jornadaAmpla;
    private IntervaloTempoSalvosubCategorias jornandaDetalhada;

    public VagaTrabalho() {}

    public VagaTrabalho(String anuncioId, String anuncianteResponsavelId, String nomeAnuncio,
                        String descricaoAnuncio, String meioDeComunicacao, String pagamento,
                        Integer quantidade, Cargo cargo, String equipamentoDeSeguranca, IntervaloTempo jornadaAmpla,
                        IntervaloTempoSalvosubCategorias jornandaDetalhada)
    {

        super(anuncioId, anuncianteResponsavelId, nomeAnuncio,
              descricaoAnuncio, meioDeComunicacao, pagamento,
              quantidade);
        this.cargo = cargo;
        this.equipamentoDeSeguranca = equipamentoDeSeguranca;
        this.jornadaAmpla = jornadaAmpla;
        this.jornandaDetalhada = jornandaDetalhada;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargoIndividuo) {
        this.cargo = cargoIndividuo;
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

    public String getEquipamentoDeSeguranca(){
        return this.equipamentoDeSeguranca;
    }

    public void setEquipamentoDeSeguranca(String equipamentoDeSeguranca){
        this.equipamentoDeSeguranca = equipamentoDeSeguranca;
    }
}
