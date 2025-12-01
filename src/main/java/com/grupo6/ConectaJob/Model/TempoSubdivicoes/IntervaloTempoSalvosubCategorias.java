package com.grupo6.ConectaJob.Model.TempoSubdivicoes;

import java.util.ArrayList;
import java.util.List;

public class IntervaloTempoSalvosubCategorias {

    private List<IntervaloTempo> jornadaCdescanco  = new ArrayList<>();
    private List<intervaloComString> jornadaSubCategorizada  = new ArrayList<>();
    public boolean jornadaCdescancoCreate (IntervaloTempo jornandaPreDescanco, IntervaloTempo intervaloDescanco, IntervaloTempo jornadaPosDesanco){
            jornadaCdescanco.add(jornandaPreDescanco);
            jornadaCdescanco.add(intervaloDescanco);
            jornadaCdescanco.add(jornadaPosDesanco);
        return true;
    }
    public  List<intervaloComString> jornadaSubCategorizadaCreate (){
        return jornadaSubCategorizada;
    }
    public IntervaloTempoSalvosubCategorias() {}

    public List<IntervaloTempo> getJornadaCdescanco() {
        return jornadaCdescanco;
    }

    public void setJornadaCdescanco(List<IntervaloTempo> jornadaCdescanco) {
        this.jornadaCdescanco = jornadaCdescanco;
    }

    public List<intervaloComString> getJornadaSubCategorizada() {
        return jornadaSubCategorizada;
    }

    public void setJornadaSubCategorizada(List<intervaloComString> jornadaSubCategorizada) {
        this.jornadaSubCategorizada = jornadaSubCategorizada;
    }
}
