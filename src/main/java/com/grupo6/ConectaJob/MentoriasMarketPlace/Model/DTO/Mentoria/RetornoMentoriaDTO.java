package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.DTO.Mentoria;

import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempo;

import java.util.List;

public class RetornoMentoriaDTO extends RetornoAnuncioDTO {

    private String formatoMentoria;
    private String nivelMentoria;
    private List<String> temasPrincipais;
    private List<IntervaloTempo> horariosDisponiveis;


    public RetornoMentoriaDTO() {}


    public RetornoMentoriaDTO(String anuncianteResponsavelId, String nomeAnuncio, String descricaoAnuncio,
                              String meioDeComunicacao, String pagamento, Integer quantidade, String formatoMentoria,
                              String nivelMentoria, List<String> temasPrincipais, List<IntervaloTempo> horariosDisponiveis)
    {
        super(anuncianteResponsavelId, nomeAnuncio, descricaoAnuncio,
                meioDeComunicacao, pagamento, quantidade);
        this.formatoMentoria = formatoMentoria;
        this.nivelMentoria = nivelMentoria;
        this.temasPrincipais = temasPrincipais;
        this.horariosDisponiveis = horariosDisponiveis ;
    }

    public String getFormatoMentoria() {
        return formatoMentoria;
    }

    public void setFormatoMentoria(String formatoMentoria) {
        this.formatoMentoria = formatoMentoria;
    }

    public String getNivelMentoria() {
        return nivelMentoria;
    }

    public void setNivelMentoria(String nivelMentoria) {
        this.nivelMentoria = nivelMentoria;
    }

    public List<String> getTemasPrincipais() {
        return temasPrincipais;
    }

    public void setTemasPrincipais(List<String> temasPrincipais) {
        this.temasPrincipais = temasPrincipais;
    }

    public List<IntervaloTempo> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(List<IntervaloTempo> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }
}
