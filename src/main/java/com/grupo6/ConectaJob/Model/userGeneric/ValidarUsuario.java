package com.grupo6.ConectaJob.Model.userGeneric;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.ValidacaoException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.grupo6.ConectaJob.ExceptionsConfig.globalExceptionsHandler;

import javax.validation.ValidationException;
import java.util.Date;

public abstract class ValidarUsuario {

    @Autowired
    private UserGenericRepository usergenericrepository;

    public void validarusuario(Usuario usuario){
        validarAtributosusuario(usuario);
        validaratributosespecificos(usuario);
    }

    public void validarAtributosusuario(Usuario usuariovalidar){
        validarcpf(usuariovalidar.getCpf());
        validarnome(usuariovalidar.getNome());
        validarsenha(usuariovalidar.getSenha());
        validardatanasc(usuariovalidar.getDtNascimento());

    }

    public boolean entradaVazia(String entrada){
        if (entrada == null || entrada.isBlank()){
            return false;
        }
        return true;
    }

    public boolean entradaMaxima(String entrada, int numero){
        if (entrada.length() > numero){
            return false;
        }
        return true;
    }

    public void validarcpf(String CPF){
        boolean tamanho = false;
        boolean digitos = true;
        var usuarionovo = usergenericrepository.findByCpf(CPF);

        if (!entradaVazia(CPF)){
            throw new ValidacaoException("cpf nao atrelado");
        }

        if (usuarionovo != null){
            throw new notFound("Usuario ja existe");
        }

        if (CPF.length() == 11){
            tamanho = true;
        }

        for (char c: CPF.toCharArray()){
            if (!Character.isDigit(c)){
                digitos = false;
            }
        }

        if (!tamanho || !digitos){
            throw new ValidacaoException("CPF invalido");
        }
    }

    public void validarnome(String Nome){
        if (!entradaVazia(Nome)){
            throw new ValidacaoException("Nome nao atrelado");
        }

        if (entradaMaxima(Nome, 100)){
            throw new ValidacaoException ("Nome tem mais de 100 caracteres");
        }
    }

    public void validarsenha(String senha){
        if (!entradaVazia(senha)){
            throw new ValidacaoException("Senha nao preenchida");
        }

        if (!entradaMaxima(senha, 50)){
            throw new ValidacaoException("Senha tem mais de 50 caracteres");
        }
        
    }

    public boolean validardatanasc(Date dtnascimento){
        if (dtnascimento == null){
            throw new ValidacaoException("Data vazia");
        }
        Date hoje = new Date();

        if(dtnascimento.after(hoje)){
            throw new ValidacaoException("Data invalida!");
        }

        return true;


    }

    public abstract void validaratributosespecificos(Usuario usuariovalidar);




}
