package com.grupo6.ConectaJob.Model.DTO.Anunciante;

import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;

public class RetornoAnuncianteDTO {
    private String nomeAnunciante;
    private String meioDeComunicacao;
    private String ftPerfilLink;

    public RetornoAnuncianteDTO(){}

    public RetornoAnuncianteDTO(String _nomeAnunciante, String _meioDeComunicacao, String _ftPerfilLink){
        this.nomeAnunciante = _nomeAnunciante;
        this.meioDeComunicacao = _meioDeComunicacao;
        this.ftPerfilLink = _ftPerfilLink;
    }

    public String getNomeAnunciante(){
        return this.nomeAnunciante;
    }

    public void setNomeAnunciante(String _nomeAnunciante){
        this.nomeAnunciante = _nomeAnunciante;
    }

    public String getMeioDeComunicacao(){
        return this.meioDeComunicacao;
    }

    public void setMeioDeComunicacao(String _meioDeComunicacao){
        this.meioDeComunicacao = _meioDeComunicacao;
    }

    public String getFtPerfilLink(){
        return this.ftPerfilLink;
    }

    public void setFtPerfilLink(String _ftPerfilLink){
        this.ftPerfilLink = _ftPerfilLink;
    }
}
