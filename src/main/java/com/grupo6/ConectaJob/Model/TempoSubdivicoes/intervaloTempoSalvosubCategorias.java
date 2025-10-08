package com.grupo6.ConectaJob.Model.TempoSubdivicoes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class intervaloTempoSalvosubCategorias {

    private List<intervaloTempo> jornadaCdescanco  = new ArrayList<>();
    private List<intervaloComString> jornadaSubCategorizada  = new ArrayList<>();
    public boolean jornadaCdescancoCreate (intervaloTempo jornandaPreDescanco, intervaloTempo intervaloDescanco, intervaloTempo jornadaPosDesanco){
            jornadaCdescanco.add(jornandaPreDescanco);
            jornadaCdescanco.add(intervaloDescanco);
            jornadaCdescanco.add(jornadaPosDesanco);
        return true;
    }
    public  List<intervaloComString> jornadaSubCategorizadaCreate (){
        return jornadaSubCategorizada;
    }
    public intervaloTempoSalvosubCategorias() {}

    public List<intervaloTempo> getJornadaCdescanco() {
        return jornadaCdescanco;
    }

    public void setJornadaCdescanco(List<intervaloTempo> jornadaCdescanco) {
        this.jornadaCdescanco = jornadaCdescanco;
    }

    public List<intervaloComString> getJornadaSubCategorizada() {
        return jornadaSubCategorizada;
    }

    public void setJornadaSubCategorizada(List<intervaloComString> jornadaSubCategorizada) {
        this.jornadaSubCategorizada = jornadaSubCategorizada;
    }
}
