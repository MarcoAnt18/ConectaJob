package com.grupo6.ConectaJob.Model.DTO;

import com.grupo6.ConectaJob.Model.userGeneric.Usuario;

import java.util.Date;

public class retornoUsuarioDTO {

    private String nome;

    private String ftperfilLink;

    private Date dtnascimento;

    public String getNome() {
        return nome;
    }

    public String getFtperfilLink() {
        return ftperfilLink;
    }

    public Date getDtnascimento() {
        return dtnascimento;
    }

    public void setFtperfilLink(String ftperfilLink) {
        this.ftperfilLink = ftperfilLink;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDtnascimento(Date dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public retornoUsuarioDTO(){

    }

    public retornoUsuarioDTO(String nome, String ftperfilLink, Date dataNascimento){
        this.nome = nome;
        this.dtnascimento = dataNascimento;
        this.ftperfilLink = ftperfilLink;
    }
}
