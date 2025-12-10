package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentor;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.ValidacaoException;
import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.Anunciante.ValidarEntradaAnunciante;
import com.grupo6.ConectaJob.Model.userEmpresa.Empresa;
import com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ValidarEntradaMentor")
public class ValidarEntradaMentor extends ValidarEntradaAnunciante {

    public void validarAtributosEspecificos(Anunciante anunciante){
        Mentor mentorValidar = (Mentor) anunciante;
        validarAreaAtuacao(mentorValidar.getAreaAtuacao());
        validarBiografia(mentorValidar.getBiografia());
        validarCertificacoes(mentorValidar.getCertificacoes());
    }

    public void validarAreaAtuacao(String areaAtuacao){
        if(!validarEntradaVazia(areaAtuacao)){
            throw new ValidacaoException("Área de atuação não informada");
        }

        if(!validarMaxCaracteres(areaAtuacao, 60)){
            throw new ValidacaoException("Área de atuação excede o limite de 60 caracteres");
        }
    }

    public void validarBiografia(String biografia){
        if(!validarEntradaVazia(biografia)){
            throw new ValidacaoException("Biografia não informada");
        }

        if(!validarMaxCaracteres(biografia, 700)){
            throw new ValidacaoException("Biografia excede o limite de 700 caracteres");
        }
    }

    public void validarCertificacoes(List<String> certificacoes){
        if(certificacoes == null){
            throw new ValidacaoException("Nenhuma certificado informado");
        }

        for(String certificado : certificacoes){

            if(!validarEntradaVazia(certificado)){
                throw new ValidacaoException("Certificado não informado");
            }

            if(!validarMaxCaracteres(certificado, 120)){
                throw new ValidacaoException("Certificado excede o limite de 120 caracteres");
            }
        }
    }
}