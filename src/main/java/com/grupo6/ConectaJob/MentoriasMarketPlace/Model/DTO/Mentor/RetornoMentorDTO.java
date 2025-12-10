package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.DTO.Mentor;

import com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoAnuncianteDTO;

import java.util.List;

public class RetornoMentorDTO extends RetornoAnuncianteDTO {

    private String areaAtuacao;
    private String biografia;
    private List<String> certificacoes;

    public RetornoMentorDTO() {}

    public RetornoMentorDTO(String nomeAnunciante, String meioDeComunicacao, String ftPerfilLink,
                            String areaAtuacao, String biografia, List<String> certificacoes)
    {
        super(nomeAnunciante, meioDeComunicacao, ftPerfilLink);
        this.areaAtuacao = areaAtuacao;
        this.biografia = biografia;
        this.certificacoes = certificacoes;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<String> getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(List<String> certificacoes) {
        this.certificacoes = certificacoes;
    }

}
