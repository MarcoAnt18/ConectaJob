package com.grupo6.ConectaJob.Model.notificacao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NotificacaoUsuarioInfo {
    private String cpf;
    private String nome;
    private LocalDate dtNascimento;

    public NotificacaoUsuarioInfo(String cpf, String nome, LocalDate dtNascimento){
        this.cpf = cpf;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }
}
