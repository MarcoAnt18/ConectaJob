package com.grupo6.ConectaJob.Model.userEmpresa;

import com.grupo6.ConectaJob.Model.listaAvaliacoesSegundoCargo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user_empresa")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class empresa {
    @Id
    private String id;
    private String CPFatrelado;
    private String CNPJ;
    private String nomeEmpresa;
    private String segmento;
    private String ftPerfilLink;
    private List<servicoPrestado> servicoPrestado;
    private listaAvaliacoesSegundoCargo avaliacoesSegundoCargo;

    public empresa(String CPFatrelado, String CNPJ, String nomeEmpresa,
                   String segmento,List<servicoPrestado> servicoPrestado) {

        this.CPFatrelado = CPFatrelado;
        this.CNPJ = CNPJ;
        this.nomeEmpresa = nomeEmpresa;
        this.segmento = segmento;
        this.servicoPrestado = servicoPrestado;
    }

    @Override
    public String toString() {
        return "empresa{" +
                "CNPJ='" + CNPJ + '\'' +
                ",nomeEmpresa='" + nomeEmpresa + '\'' +
                ",segmento='" + segmento + '\'' +
                ",ftPerfilLink='" + ftPerfilLink + '\'' +
                ",servicoPrestado=" + servicoPrestado +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCPFatrelado() {
        return CPFatrelado;
    }

    public void setCPFatrelado(String CPFatrelado) {
        this.CPFatrelado = CPFatrelado;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getFtPerfilLink() {
        return ftPerfilLink;
    }

    public void setFtPerfilLink(String ftPerfilLink) {
        this.ftPerfilLink = ftPerfilLink;
    }

    public List<com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado> getServicoPrestado() {
        return servicoPrestado;
    }

    public void setServicoPrestado(List<com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado> servicoPrestado) {
        this.servicoPrestado = servicoPrestado;
    }

    public listaAvaliacoesSegundoCargo getAvaliacoesSegundoCargo() {
        return avaliacoesSegundoCargo;
    }

    public void setAvaliacoesSegundoCargo(listaAvaliacoesSegundoCargo avaliacoesSegundoCargo) {
        this.avaliacoesSegundoCargo = avaliacoesSegundoCargo;
    }
}
