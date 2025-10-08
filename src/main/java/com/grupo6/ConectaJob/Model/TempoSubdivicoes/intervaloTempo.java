package com.grupo6.ConectaJob.Model.TempoSubdivicoes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class intervaloTempo {

    @JsonFormat(pattern = "HH:mm")
    private LocalTime entrada;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime saida;

    public intervaloTempo() {}
}
