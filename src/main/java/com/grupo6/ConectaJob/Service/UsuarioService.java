package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.RetornoTrabalhadorDTO;
import com.grupo6.ConectaJob.Model.DTO.SearchCPF;
import com.grupo6.ConectaJob.Model.DTO.retornoUsuarioDTO;
import com.grupo6.ConectaJob.Model.userGeneric.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuariorepository;

    @Autowired
    public ValidarUsuario validadorUsuario;


    public boolean CreateUsuario(Usuario novousuario){
        validadorUsuario.validarusuario(novousuario);

        usuariorepository.save(novousuario);
        return true;

    }

    public boolean DeleteUsuario(SearchCPF searchcpfDTO){
        var usuario = BuscarUsuarioBD(searchcpfDTO.CPF());

        if (usuario == null){
            throw new notFound("Usuario não encontrado");
        }

        usuariorepository.delete(usuario);
        return true;
    }

    public Usuario BuscarUsuarioBD(String CPF){
        Usuario usuario = usuariorepository.findusuariobycpf(CPF);

        if (usuario == null){
            throw new notFound("usuario nao encontrado!");
        }

        return usuario;
    }

    public retornoUsuarioDTO BuscarUsuario(SearchCPF cpfTrabalhador){

        Usuario usuariobuscado = BuscarUsuarioBD(cpfTrabalhador.CPF());

        StrategyRetornoUsuarioDTO criarUsuario = new CriarRetornoUsuarioDTO();
        return criarUsuario.criarretornoUsuario(usuariobuscado);

    }

    public boolean editUsuario(SearchCPF searchcpfDTO, Usuario novousuario){
        Usuario usuarioparaAtualizar = BuscarUsuarioBD(searchcpfDTO.CPF());

        StrategyAtualizarUsuario usuarioAtualizado = new AtualizarUsuario();

        usuarioAtualizado.AtualizarUsuario(usuarioparaAtualizar, novousuario);

        usuariorepository.save(usuarioparaAtualizar);

        return true;

    }



}
