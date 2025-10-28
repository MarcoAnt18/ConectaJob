package com.grupo6.ConectaJob.Model.JornadaDeTrabalho;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.DuplicateEntityException;
import com.grupo6.ConectaJob.Model.RegistroPonto.RegistroPonto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "jornada_de_trabalho")
public class JornadaDeTrabalho {
    @Id
    private String id;
    private List<RegistroPonto> registroDePontos;
    private String usuarioAtreladoCPF;
    private String empresaAtreladaCNPJ;
    private String vagaAtrelada;

    public JornadaDeTrabalho(String usuarioAtreladoCPF, String empresaAtreladaCNPJ, String vagaAtrelada){
        this.usuarioAtreladoCPF = usuarioAtreladoCPF;
        this.empresaAtreladaCNPJ = empresaAtreladaCNPJ;
        this.vagaAtrelada = vagaAtrelada;
        this.registroDePontos = new ArrayList<>();
    }

    public boolean marcarEntrada(){
        RegistroPonto pontoAtual = procurarPontoAtual();

        if(pontoAtual != null){
            return false;
        }

        //Adiciona o ponto
        RegistroPonto ponto = new RegistroPonto();
        registroDePontos.add(ponto);
        return true;
    }

    public boolean marcarSaida(){
        RegistroPonto pontoAtual = procurarPontoAtual();

        //Marca a saída no ponto já aberto
        if(pontoAtual != null){
            //Erro caso tente marcar mais de uma saída no mesmo dia
            if(pontoAtual.getHoraSaida() != null){
                return false;
            }

            pontoAtual.marcarSaida();
        }
        //Caso não encontre um ponto aberto, cria um para marcar a saída
        else{
            RegistroPonto novoPonto = new RegistroPonto();
            novoPonto.marcarSaida();
            novoPonto.setHoraEntrada(null);
            registroDePontos.add(novoPonto);
        }
        return true;
    }

    //Busca pelo ponto com a data atual
    private RegistroPonto procurarPontoAtual(){
        RegistroPonto pontoAtual = null;
        LocalDate dataAtual = LocalDate.now(ZoneId.of("America/Sao_Paulo"));

        for(RegistroPonto ponto: registroDePontos){
            if(ponto.getData().isEqual(dataAtual)){
                pontoAtual = ponto;
            }
        }

        return pontoAtual;
    }
}
