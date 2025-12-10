package com.grupo6.ConectaJob.Model.Anunciante;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.DuplicateEntityException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.ValidacaoException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class ValidarEntradaAnunciante {

    @Autowired
    protected UserGenericRepository userGenericRepository;

    @Autowired
    protected AnuncianteRepository anuncianteRepository;

    public void validarAnunciante(Anunciante anunciante){
        validarAtributosAnunciante(anunciante);
        validarAtributosEspecificos(anunciante);
    }

    public void validarAtributosAnunciante(Anunciante anuncianteValidar){
        validarCPF(anuncianteValidar.getCpfAtrelado());
        validarAnuncianteDuplicado(anuncianteValidar.getCpfAtrelado());
        validarNomeAnunciante(anuncianteValidar.getNomeAnunciante());
        validarMeioDeComunicacaoo(anuncianteValidar.getMeioDeComunicacao());
        validarftPerfilLink(anuncianteValidar.getFtPerfilLink());
    }

    public void validarCPF(String CPF){

        //Verifica se o CPF é vazio
        if(!validarEntradaVazia(CPF)){
            throw new ValidacaoException("CPF Atrelado não informado");
        }

        //Verifica quantidade de dígitos
        boolean tamanho = true;
        if (CPF.length() != 11){
            tamanho = false;
        }

        //Verifica se todos os caractéres são números
        boolean digitos = true;
        for (char c : CPF.toCharArray()) {
            if (!Character.isDigit(c)) {
                digitos = false;
            }
        }

        if(!tamanho || !digitos){
            throw new ValidacaoException("CPF inválido");
        }

        //Verifica se o usuário que a empresa vai ser atrelada existe
        var representante = userGenericRepository.findByCpf(CPF);

        if (representante == null){
            throw new notFound("Usuário com este CPF não encontrado");
        }
    }

    public void validarNomeAnunciante(String nomeAnunciante){
        if (!validarEntradaVazia(nomeAnunciante)){
            throw new ValidacaoException("Nome do anunciante não informado");
        }

        if(!validarMaxCaracteres(nomeAnunciante, 100)){
            throw new ValidacaoException("Nome do Anunciante excede o limite de 100 caracteres");
        }
    }

    public void validarMeioDeComunicacaoo(String meioDeComunicacao){
        if (!validarEntradaVazia(meioDeComunicacao)){
            throw new ValidacaoException("Nome do anunciante não informado");
        }

        if(!validarMaxCaracteres(meioDeComunicacao, 50)){
            throw new ValidacaoException("Meio de comunicação excede o limite de 50 caracteres");
        }
    }

    public void validarftPerfilLink(String ftPerfilLink){
        if (!validarEntradaVazia(ftPerfilLink)){
            throw new ValidacaoException("Nome do anunciante não informado");
        }

        if (!ftPerfilLink.startsWith("http://")){
            throw new ValidacaoException("Link de foto de perfil inválido");

        }
    }

    public boolean validarEntradaVazia(String entrada){
        if (entrada == null || entrada.isBlank()){
            return false;
        }

        return true;
    }

    public boolean validarMaxCaracteres(String entrada, int numMaximo){
        if (entrada.length() > numMaximo){
            return false;
        }

        return true;

    }

    public void validarAnuncianteDuplicado(String CPFAtrelado){
        var buscaAnunciante = anuncianteRepository.findAnuncianteByCpfAtrelado(CPFAtrelado);

        if (buscaAnunciante != null){
            throw new DuplicateEntityException("O CPF informado já possui um anunciante cadastrado");
        }
    }

    public abstract void validarAtributosEspecificos(Anunciante anunciante);
}
