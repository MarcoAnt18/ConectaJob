package com.grupo6.ConectaJob.Model.Anuncio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "anuncio")
public class Anuncio {

    @Id
    private String anuncioId;
    private String anuncianteResponsavelId;
    private String nomeAnuncio;
    private String descricaoAnuncio;
    private String meioDeComunicacao;
    private String pagamento;
    private Integer quantidade;

    public Anuncio() {}

    public Anuncio(String _anuncioId, String _anuncianteResponsavelId, String _nomeAnuncio,
                   String _descricaoAnuncio, String _meioDeComunicacao, String _pagamento,
                   Integer _quantidade)
    {
        this.anuncioId = _anuncioId;
        this.anuncianteResponsavelId = _anuncianteResponsavelId;
        this.nomeAnuncio = _nomeAnuncio;
        this.descricaoAnuncio = _descricaoAnuncio;
        this.meioDeComunicacao = _meioDeComunicacao;
        this.pagamento = _pagamento;
        this.quantidade = _quantidade;
    }

    public String getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(String anuncioId) {
        this.anuncioId = anuncioId;
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

    public void atualizarAnuncio(Anuncio novoAnuncio){
        atualizarAtibutosComuns(novoAnuncio);
        atualizarAtributosEspecificos(novoAnuncio);
    }

    public void atualizarAtibutosComuns(Anuncio novoAnuncio){
        if (novoAnuncio.getNomeAnuncio() != null) this.nomeAnuncio = novoAnuncio.getNomeAnuncio();
        if (novoAnuncio.getDescricaoAnuncio() != null) this.descricaoAnuncio = novoAnuncio.getDescricaoAnuncio();
        if (novoAnuncio.getDescricaoAnuncio() != null) this.meioDeComunicacao = novoAnuncio.getMeioDeComunicacao();
        if (novoAnuncio.getPagamento() != null) this.pagamento = novoAnuncio.getPagamento();
        if (novoAnuncio.getQuantidade() != null) this.quantidade = novoAnuncio.getQuantidade();
    }

    public void atualizarAtributosEspecificos(Anuncio novoAnuncio){}
}
