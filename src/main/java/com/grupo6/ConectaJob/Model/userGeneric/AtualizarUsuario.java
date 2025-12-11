package com.grupo6.ConectaJob.Model.userGeneric;

public class AtualizarUsuario implements StrategyAtualizarUsuario{

    @Override
    public void AtualizarUsuario(Usuario usuarioAtualizar, Usuario novousuario) {
        Usuario usuarioAtualizado = (Usuario) usuarioAtualizar;

        usuarioAtualizado.atualizarUsuario(novousuario);


    }
}
