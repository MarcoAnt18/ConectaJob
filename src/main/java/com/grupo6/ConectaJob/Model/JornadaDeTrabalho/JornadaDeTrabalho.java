package com.grupo6.ConectaJob.Model.JornadaDeTrabalho;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.RegistroPonto.RegistroPonto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "jornada_de_trabalho")
public class JornadaDeTrabalho {
    @Id
    private String id;
    private List<RegistroPonto> registroDePontos;
    private String usuarioAtreladoCPF;
    private String empresaAtreladaCNPJ;
    private String vagaAtrelada;

    public JornadaDeTrabalho(String usuarioCPF, String empresaCNPJ, String nomeVaga){
        this.registroDePontos = new ArrayList<>();
        this.usuarioAtreladoCPF = usuarioCPF;
        this.empresaAtreladaCNPJ = empresaCNPJ;
        this.vagaAtrelada = nomeVaga;
    }

    public boolean marcarEntrada(){
        //Verifica se já foi registrado a entrada no mesmo dia
        LocalDate dataAtual = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        for(RegistroPonto ponto: registroDePontos){
            if(ponto.getData().isEqual(dataAtual)){
                return false;
            }
        }

        //Adiciona o ponto
        RegistroPonto ponto = new RegistroPonto();
        registroDePontos.add(ponto);
        return true;
    }

    public void marcarSaida(){
        //Verifica se já tem um ponto em aberto no mesmo dia
        LocalDate dataAtual = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        RegistroPonto pontoAtual = null;
        for(RegistroPonto ponto: registroDePontos){
            if(ponto.getData().isEqual(dataAtual)){
                pontoAtual = ponto;
            }
        }

        //Marca a saída no ponto já aberto
        if(pontoAtual != null){
            pontoAtual.marcarSaida();
        }
        //Caso não encontre um ponto aberto, cria um para marcar a saída
        else{
            RegistroPonto novoPonto = new RegistroPonto();
            novoPonto.marcarSaida();
            novoPonto.setHoraEntrada(null);
        }
    }
}
