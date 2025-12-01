package com.grupo6.ConectaJob.Model.TempoSubdivicoes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class IntervaloTempo {

    @JsonFormat(pattern = "HH:mm")
    private LocalTime entrada;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime saida;

    public IntervaloTempo() {}
}
