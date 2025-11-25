package com.grupo6.ConectaJob.Model.userEmpresa;

import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.listaAvaliacoesSegundoCargo;
import com.grupo6.ConectaJob.Model.notificacao.Notificacao;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

public class Empresa extends Anunciante {
    private String cnpjEmpresa;
    private String segmento;
    private List<servicoPrestado> servicoPrestado;
    private listaAvaliacoesSegundoCargo avaliacoesSegundoCargo;

    public Empresa(){}

    public Empresa(String _cpfAtrelado, String _nomeAnunciante, String _meioDeComunicacao, String _ftPerfilLink,
                   String _cnpjEmpresa, String segmento, List<servicoPrestado> servicoPrestado
    ){
        super(_cpfAtrelado, _nomeAnunciante, _meioDeComunicacao, _ftPerfilLink);
        this.cnpjEmpresa = _cnpjEmpresa;
        this.segmento = segmento;
        this.servicoPrestado = servicoPrestado;
    }

    @Override
    public String toString() {
        return "empresa{" +
                "CNPJ='" + cnpjEmpresa + '\'' +
                ",segmento='" + segmento + '\'' +
                ",servicoPrestado=" + servicoPrestado +
                '}';
    }

    public String getCnpjEmpresa(){
        return this.cnpjEmpresa;
    }

    public void setCnpjEmpresa(String _cnpjEmpresa){
        this.cnpjEmpresa = _cnpjEmpresa;
    }

    public String getSegmento() {
        return this.segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public List<com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado> getServicoPrestado() {
        return this.servicoPrestado;
    }

    public void setServicoPrestado(List<com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado> servicoPrestado) {
        this.servicoPrestado = servicoPrestado;
    }

    public listaAvaliacoesSegundoCargo getAvaliacoesSegundoCargo() {
        return this.avaliacoesSegundoCargo;
    }

    public void setAvaliacoesSegundoCargo(listaAvaliacoesSegundoCargo avaliacoesSegundoCargo) {
        this.avaliacoesSegundoCargo = avaliacoesSegundoCargo;
    }
}
