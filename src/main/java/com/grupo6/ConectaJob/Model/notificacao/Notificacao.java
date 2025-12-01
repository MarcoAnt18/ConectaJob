package com.grupo6.ConectaJob.Model.notificacao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//Precisa fazer: atributos, métodos getters e setters e construtor parametrizado
@Getter
@Setter
@Builder
public class Notificacao {
    private NotificacaoUsuarioInfo usuarioInfos;
    private String contatoUsuario;
    private String acao;
    private NotificacaoVagaInfo vagaTrabalhoInfos;

    public Notificacao(NotificacaoUsuarioInfo usuarioInfos, String contatoUsuario, String acao, NotificacaoVagaInfo vagaTrabalhoInfos){
        this.usuarioInfos = usuarioInfos;
        this.contatoUsuario = contatoUsuario;
        this.acao = acao;
        this.vagaTrabalhoInfos = vagaTrabalhoInfos;
    }
}
