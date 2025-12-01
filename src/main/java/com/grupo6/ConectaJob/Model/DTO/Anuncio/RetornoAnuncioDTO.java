package com.grupo6.ConectaJob.Model.DTO.Anuncio;

public class RetornoAnuncioDTO {

    String anuncianteResponsavelId;
    String nomeAnuncio;
    String descricaoAnuncio;
    String meioDeComunicacao;
    String pagamento;
    Integer quantidade;

    public RetornoAnuncioDTO() {
    }

    public RetornoAnuncioDTO(String anuncianteResponsavelId, String nomeAnuncio, String descricaoAnuncio,
                             String meioDeComunicacao, String pagamento, Integer quantidade)
    {
        this.anuncianteResponsavelId = anuncianteResponsavelId;
        this.nomeAnuncio = nomeAnuncio;
        this.descricaoAnuncio = descricaoAnuncio;
        this.meioDeComunicacao = meioDeComunicacao;
        this.pagamento = pagamento;
        this.quantidade = quantidade;
    }

    public String getAnuncianteResponsavelId() {
        return anuncianteResponsavelId;
    }

    public void setAnuncianteResponsavelId(String anuncianteResponsavelId) {
        this.anuncianteResponsavelId = anuncianteResponsavelId;
    }

    public String getNomeAnuncio() {
        return nomeAnuncio;
    }

    public void setNomeAnuncio(String nomeAnuncio) {
        this.nomeAnuncio = nomeAnuncio;
    }

    public String getDescricaoAnuncio() {
        return descricaoAnuncio;
    }

    public void setDescricaoAnuncio(String descricaoAnuncio) {
        this.descricaoAnuncio = descricaoAnuncio;
    }

    public String getMeioDeComunicacao() {
        return meioDeComunicacao;
    }

    public void setMeioDeComunicacao(String meioDeComunicacao) {
        this.meioDeComunicacao = meioDeComunicacao;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
