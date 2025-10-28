package com.grupo6.ConectaJob.Model.RegistroPonto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class RegistroPonto {
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    public RegistroPonto(){
        this.data = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        this.horaEntrada = LocalTime.now(ZoneId.of("America/Sao_Paulo")).withNano(0);
        this.horaSaida = null;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public LocalDate getData(){
        return this.data;
    }

    public void setHoraEntrada(LocalTime hora){
        this.horaEntrada = hora;
    }

    public LocalTime getHoraEntrada(){
        return this.horaEntrada;
    }

    public void setHoraSaida(LocalTime hora){
        this.horaSaida = hora;
    }

    public LocalTime getHoraSaida(){
        return this.horaSaida;
    }

    public void marcarSaida(){
        this.horaSaida = LocalTime.now(ZoneId.of("America/Sao_Paulo")).withNano(0);
    }
}
