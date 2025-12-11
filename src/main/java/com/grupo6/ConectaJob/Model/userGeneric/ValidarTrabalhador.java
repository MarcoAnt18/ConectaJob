package com.grupo6.ConectaJob.Model.userGeneric;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.ValidacaoException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.userTrabalhador;
import com.grupo6.ConectaJob.Model.vaga.TrabalhadorRepository;

public abstract class ValidarTrabalhador extends ValidarUsuario{

    private TrabalhadorRepository usertrabalhador;

    @Override
    public void validaratributosespecificos(Usuario usuariovalidar) {
        userTrabalhador trabalhador = (userTrabalhador) usuariovalidar;
        validarAtributosTrabalhador(trabalhador);
    }

    public void validarAtributosTrabalhador(userTrabalhador trabalhador){
        validarcep(trabalhador.getCEP());
        validaremail(trabalhador.getEmail());
        validarformacao(trabalhador.getFormacao());

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

    public void validarcep(String CEP){
        boolean tamanho = false;
        boolean digitos = true;
        var funcionario = usertrabalhador.findbyCEP(CEP);

        if (!entradaVazia(CEP)){
            throw new ValidacaoException("cep nao atrelado");
        }

        if (funcionario != null){
            throw new notFound("Usuario ja existe");
        }

        if (CEP.length() == 8){
            tamanho = true;
        }

        for (char c: CEP.toCharArray()){
            if (!Character.isDigit(c)){
                digitos = false;
            }
        }

        if (!tamanho || !digitos){
            throw new ValidacaoException("CEP invalido");
        }
    }

    public void validaremail(String email){
        if(!entradaVazia(email)){
            throw new ValidacaoException("Email invalido");
        }

        if(entradaMaxima(email, 100)){
            throw new ValidacaoException("Email invalido");
        }
    }

    public void validarformacao(String formacao){
        if(!entradaVazia(formacao)){
            throw new ValidacaoException("escolaridade nao preenchida");
        }

        if(entradaMaxima(formacao, 100)){
            throw new ValidacaoException("resposta invalida");
        }
    }
}
