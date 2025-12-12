package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentoria;

import com.grupo6.ConectaJob.MentoriasMarketPlace.Model.DTO.Mentoria.RetornoMentoriaDTO;
import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.StrategyRetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoVagaDTO;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import org.springframework.stereotype.Component;

@Component("CriarRetornoMentoriaDTO")
public class CriarRetornoMentoriaDTO implements StrategyRetornoAnuncioDTO {

    public RetornoAnuncioDTO criarRetornoAnuncioDTO(Anuncio anuncio){
        Mentoria mentoria = (Mentoria) anuncio;

        return new RetornoMentoriaDTO(
                mentoria.getAnuncianteResponsavelId(),
                mentoria.getNomeAnuncio(),
                mentoria.getDescricaoAnuncio(),
                mentoria.getMeioDeComunicacao(),
                mentoria.getPagamento(),
                mentoria.getQuantidade(),
                mentoria.getFormatoMentoria(),
                mentoria.getNivelMentoria(),
                mentoria.getTemasPrincipais(),
                mentoria.getHorariosDisponiveis()
        );
    }
}
