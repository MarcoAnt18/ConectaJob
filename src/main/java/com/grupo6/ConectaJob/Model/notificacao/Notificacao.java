package com.grupo6.ConectaJob.Model.notificacao;

import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//Precisa fazer: atributos, métodos getters e setters e construtor parametrizado
@Getter
@Setter
@Builder
public class Notificacao {
    private userGeneric usuario;
    private String contatoUsuario;
    private String acao;
    private vagaTrabalho vagaTrabalho;

    public Notificacao(userGeneric usuario, String contatoUsuario, String acao, vagaTrabalho vagaTrabalho){
        this.usuario = usuario;
        this.contatoUsuario = contatoUsuario;
        this.acao = acao;
        this.vagaTrabalho = vagaTrabalho;
    }
}
