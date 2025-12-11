package com.grupo6.ConectaJob.Model.userGeneric;

import java.util.Date;

public class Usuario extends userGeneric{

    private String nome;

    private String ftperfilLink;

    private Date dtNascimento;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public void setFtperfilLink(String ftperfilLink) {
        this.ftperfilLink = ftperfilLink;
    }

    public String getNome() {
        return nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public String getFtperfilLink() {
        return ftperfilLink;
    }

    public void atualizarUsuario(Usuario novousuario){
        atualizarAtributoscomuns(novousuario);
        atualizarAtributosespecificos(novousuario);
    }

    public void atualizarAtributoscomuns(Usuario novousuario){
        if (novousuario.getNome() != null) this.nome = novousuario.getNome();
        if (novousuario.getDtNascimento() != null) this.dtNascimento = novousuario.getDtNascimento();
        if (novousuario.getFtperfilLink() != null) this.ftperfilLink = novousuario.getFtperfilLink();
    }

    public void atualizarAtributosespecificos(Usuario novousuario){

    }
}
