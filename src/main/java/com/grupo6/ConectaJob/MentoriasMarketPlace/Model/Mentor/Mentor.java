package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentor;

import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempo;

import java.util.ArrayList;
import java.util.List;

public class Mentor extends Anunciante {

    private String areaAtuacao;
    private String biografia;
    private List<String> certificacoes;

    public Mentor() {}

    public Mentor(String cpfAtrelado, String nomeAnunciante, String meioDeComunicacao,
                  String ftPerfilLink, String areaAtuacao, String biografia, List<String> certificacoes)
    {
        super(cpfAtrelado, nomeAnunciante, meioDeComunicacao, ftPerfilLink);
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

    public void atualizarAtributosEspecificos(Anunciante novoAnunciante) {
        Mentor novoMentor = (Mentor) novoAnunciante;

        if (novoMentor.getAreaAtuacao() != null) this.areaAtuacao = novoMentor.getAreaAtuacao();
        if (novoMentor.getBiografia() != null) this.biografia = novoMentor.getBiografia();
        if (novoMentor.getCertificacoes() != null) this.certificacoes = novoMentor.getCertificacoes();
    }
}
