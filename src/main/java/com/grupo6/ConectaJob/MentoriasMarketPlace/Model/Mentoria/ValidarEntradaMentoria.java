package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentoria;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.ValidacaoException;
import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.ValidarEntradaAnuncio;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ValidarEntradaMentoria")
public class ValidarEntradaMentoria extends ValidarEntradaAnuncio {

    public void validarAtributosEspecificos(Anuncio anuncioValidar){
        Mentoria mentoriaValidar = (Mentoria) anuncioValidar;

        validarFormatoMentoria(mentoriaValidar.getFormatoMentoria());
        validarNivelMentoria(mentoriaValidar.getNivelMentoria());
        validarTemasPrincipais(mentoriaValidar.getTemasPrincipais());
        validarHorariosDisponiveis(mentoriaValidar.getHorariosDisponiveis());
    }

    private void validarFormatoMentoria(String formatoMentoria){
        validarStringNula(formatoMentoria, "Formato da Mentoria");
        validarTamanhoString(formatoMentoria, "Formato da Mentoria", 3, 50);
    }
    
    private void validarNivelMentoria(String nivelMentoria){
        validarStringNula(nivelMentoria, "Nível da mentoria");
        validarTamanhoString(nivelMentoria, "Nível da mentoria", 3, 40);
    }

    private void validarTemasPrincipais(List<String> temasPrincipais){
        if(temasPrincipais == null){
            throw new ValidacaoException("Nenhum Tema principal");
        }

        for(String tema : temasPrincipais){

            validarStringNula(tema, "Tema principal");

            validarTamanhoString(tema, "Tema principal", 3, 50);
        }
    }

    private void validarHorariosDisponiveis(List<IntervaloTempo> horariosDisponiveis){
        if(horariosDisponiveis == null){
            throw new ValidacaoException("Nenhum Horário disponível informado");
        }

        for(IntervaloTempo horario : horariosDisponiveis){
            if (horario == null){
                throw new ValidacaoException("Horário Disponíveis não pode ser nulo");
            }

            if (horario.getEntrada() == null || horario.getSaida() == null) {
                throw new IllegalArgumentException("Entrada e saída de Horários Disponíveis são obrigatórias.");
            }

            if (horario.getEntrada().isAfter(horario.getSaida())){
                throw new ValidacaoException("Em Horários Disponiveis a hora de entrada deve ser antes da hora de saída");
            }
        }
    }
}