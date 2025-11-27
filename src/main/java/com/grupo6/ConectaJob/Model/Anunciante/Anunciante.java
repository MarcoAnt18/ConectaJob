package com.grupo6.ConectaJob.Model.Anunciante;

import com.grupo6.ConectaJob.Model.notificacao.Notificacao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_anunciante")
public class Anunciante {
    @Id
    String id;
    private String cpfAtrelado;
    private String nomeAnunciante;
    private List<Notificacao> notificacoes;
    private String meioDeComunicacao;
    private String ftPerfilLink;

    public Anunciante(){}

    public Anunciante(String _cpfAtrelado, String _nomeAnunciante, String _meioDeComunicacao, String _ftPerfilLink){
        this.cpfAtrelado = _cpfAtrelado;
        this.nomeAnunciante = _nomeAnunciante;
        this.notificacoes = new ArrayList<>();
        this.meioDeComunicacao = _meioDeComunicacao;
        this.ftPerfilLink = _ftPerfilLink;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String _id){
        this.id = _id;
    }

    public String getCpfAtrelado(){
        return this.cpfAtrelado;
    }

    public void setCpfAtrelado(String _cpfAtrelado){
        this.cpfAtrelado = _cpfAtrelado;
    }


    public String getNomeAnunciante(){
        return this.nomeAnunciante;
    }

    public void setNomeAnunciante(String _nomeAnunciante){
        this.nomeAnunciante = _nomeAnunciante;
    }

    public List<Notificacao> getNotificacoes(){
        return this.notificacoes;
    }

    public void setNotificacoes(List<Notificacao> _notificacoes){
        this.notificacoes = _notificacoes;
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

    //Gerenciamento de notificações futuro
    /*
    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }
    public void setNotificacoes(Notificacao notificacao){
        this.notificacoes.add(notificacao);
    }
    public void deleteNotificacao(Notificacao notificacao){
        this.notificacoes.remove(notificacao);
    }
     */

    public final void atualizarAnunciante(Anunciante novoAnunciante){
        atualizarAtributosComuns(novoAnunciante);
        atualizarAtributosEspecificos(novoAnunciante);
    }

    public void atualizarAtributosComuns(Anunciante novoAnunciante){
        if (novoAnunciante.getCpfAtrelado() != null) this.cpfAtrelado = novoAnunciante.getCpfAtrelado();
        if (novoAnunciante.getNomeAnunciante() != null) this.nomeAnunciante = novoAnunciante.getNomeAnunciante();
        if (novoAnunciante.getNotificacoes() != null) this.notificacoes = novoAnunciante.getNotificacoes();
        if (novoAnunciante.getMeioDeComunicacao() != null) this.meioDeComunicacao = novoAnunciante.getMeioDeComunicacao();
        if (novoAnunciante.getFtPerfilLink() != null) this.ftPerfilLink = novoAnunciante.getFtPerfilLink();
    }


    public void atualizarAtributosEspecificos(Anunciante novoAnunciante){

    }

}
