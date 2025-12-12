package com.grupo6.ConectaJob.Model.userEmpresa;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.ValidacaoException;
import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.Anunciante.ValidarEntradaAnunciante;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ValidarEntradaEmpresa")
public class ValidarEntradaEmpresa extends ValidarEntradaAnunciante {

    public void validarAtributosEspecificos(Anunciante anunciante){
        Empresa empresaValidar = (Empresa) anunciante;
        validarCNPJ(empresaValidar.getCnpjEmpresa());
        validarServicoPrestado(empresaValidar.getServicoPrestado());
    }

    private void validarCNPJ(String CNPJ){
        //Verifica se o CNPJ é vazio
        if(!validarEntradaVazia(CNPJ)){
            throw new ValidacaoException("CNPJ não informado");
        }

        //Verifica quantidade de dígitos
        boolean tamanho = true;
        if (CNPJ.length() != 14){
            tamanho = false;
        }

        //Verifica se todos os caractéres são números
        boolean digitos = true;
        for (char c : CNPJ.toCharArray()) {
            if (!Character.isDigit(c)) {
                digitos = false;
            }
        }

        if(!tamanho || !digitos){
            throw new ValidacaoException("CNPJ inválido");
        }
    }

    private void validarSegmento(String segmento){
        if(!validarEntradaVazia(segmento)){
            throw new ValidacaoException("Segmento não informado");
        }

        if(validarMaxCaracteres(segmento, 60)){
            throw new ValidacaoException("Segmento excede o limite de 60 caracteres");
        }
    }

    private void validarServicoPrestado(List<servicoPrestado> servicoPrestado){
        if(servicoPrestado == null || servicoPrestado.isEmpty()){
            throw new ValidacaoException("Nenhum serviço prestado informado");
        }

        for(servicoPrestado servico : servicoPrestado){
            //Valida nome do serviço
            String nomeSerico = servico.getNomeServico();
            if(!validarEntradaVazia(nomeSerico)){
                throw new ValidacaoException("Todo serviço prestado deve ter um nome");
            }

            if(!validarMaxCaracteres(nomeSerico, 60)){
                throw new ValidacaoException("Nome do serviço excede o limite de 60 caracteres");
            }

            //Valida descrição do serviço
            String descricaoSerivco = servico.getDescricaoDoServico();
            if(!validarEntradaVazia(descricaoSerivco)){
                throw new ValidacaoException("Todo serviço prestado deve ter uma descrição");
            }

            if(!validarMaxCaracteres(descricaoSerivco, 200)){
                throw new ValidacaoException("Descrição do serviço excede o limite de 200 caracteres");
            }
        }
    }
}
