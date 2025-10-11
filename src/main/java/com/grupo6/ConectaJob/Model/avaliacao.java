package com.grupo6.ConectaJob.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "avaliacoes")
@NoArgsConstructor
@AllArgsConstructor


public class avaliacao {
    @Id
    private String id;
    private String Avaliador;
    private String CNPJAvaliador;
    private int nota;
    private String Comentario;

    public avaliacao(String avaliador, String CNPJ, int nota, String Comentario){
        this.Avaliador = avaliador;
        this.CNPJAvaliador = CNPJ;
        this.nota = nota;
        this.Comentario = Comentario;

    }

    public void setAvaliador(String avaliador) {
        Avaliador = avaliador;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setCNPJAvaliador(String CNPJAvaliador) {
        this.CNPJAvaliador = CNPJAvaliador;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public String getAvaliador() {
        return Avaliador;
    }

    public int getNota() {
        return nota;
    }

    public String getCNPJAvaliador() {
        return CNPJAvaliador;
    }

    public String getComentario() {
        return Comentario;
    }

    public String getId() {
        return id;
    }
}
