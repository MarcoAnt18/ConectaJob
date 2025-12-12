package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentor;

import com.grupo6.ConectaJob.MentoriasMarketPlace.Model.DTO.Mentor.RetornoMentorDTO;
import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.Anunciante.StrategyRetornoAnuncianteDTO;
import com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoAnuncianteDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("CriarRetornoMentorDTO")
public class CriarRetornoMentorDTO implements StrategyRetornoAnuncianteDTO {

    public RetornoAnuncianteDTO CriarRetornoAnuncianteDTO(Anunciante anunciante){
        Mentor mentor = (Mentor) anunciante;

        return new RetornoMentorDTO(
                mentor.getNomeAnunciante(),
                mentor.getMeioDeComunicacao(),
                mentor.getFtPerfilLink(),
                mentor.getAreaAtuacao(),
                mentor.getBiografia(),
                mentor.getCertificacoes()
        );
    }
}
