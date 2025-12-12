package com.grupo6.ConectaJob.Model.Anuncio;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.ValidacaoException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.Anunciante.AnuncianteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public abstract class ValidarEntradaAnuncio {

    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    public void validarEntradaAnuncio(Anuncio anuncioValidar){
        validarAtributosAnuncio(anuncioValidar);
        validarAtributosEspecificos(anuncioValidar);
    }

    protected void validarAtributosAnuncio(Anuncio anuncioValidar){
        validarAnuncianteResponsavelId(anuncioValidar.getAnuncianteResponsavelId());
        validarNomeAnuncio(anuncioValidar.getNomeAnuncio());
        validarAnuncioDuplicado(anuncioValidar.getNomeAnuncio(), anuncioValidar.getAnuncianteResponsavelId());
        validarDescricaoAnuncio(anuncioValidar.getDescricaoAnuncio());
        validarMeioDeComunicacao(anuncioValidar.getDescricaoAnuncio());
        validarPagamento(anuncioValidar.getPagamento());
        validarQuantidade(anuncioValidar.getQuantidade());
    }

    protected void validarAnuncianteResponsavelId(String AnuncianteResponsavelId){
        validarStringNula(AnuncianteResponsavelId, "AnuncianteResponsavelId");

        //Verifica se o Anunciante Responsável existe
        var anuncianteResponvalel = anuncianteRepository.findAnuncianteById(AnuncianteResponsavelId);

        if (anuncianteResponvalel == null){
            throw new notFound("Anunciante atrelado com este ID não encontrado");
        }
    }

    protected void validarNomeAnuncio(String nome){
        validarStringNula(nome, "Nome do Anúncio");
        validarTamanhoString(nome, "Nome do Anúncio", 3, 100);
    }

    protected void validarAnuncioDuplicado(String nomeAnuncio, String AnuncianteResponsavelId){
        //Verifica se o anuncio já foi cadastrado
        List<Anuncio> anuncios = anuncioRepository.findAll();

        //Percorre os anúncios cadastrados buscando pelo nome e ID do anunciante informado
        for(Anuncio anuncio : anuncios){
            if(Objects.equals(anuncio.getNomeAnuncio(), nomeAnuncio) &&
               Objects.equals(anuncio.getAnuncianteResponsavelId(), AnuncianteResponsavelId)){
                throw new ValidacaoException("Anuncio já cadastrado");
            }
        }
    }

    protected void validarDescricaoAnuncio(String descricao){
        validarStringNula(descricao, "Descrição");
        validarTamanhoString(descricao, "Descrição", 10, 500);
    }

    protected void validarMeioDeComunicacao(String meioDeComunicacao){
        validarStringNula(meioDeComunicacao, "Meio de Comunicação");
        validarTamanhoString(meioDeComunicacao, "Meio de Comunicação", 3, 400);
    }

    protected void validarPagamento(String pagamento){
        validarStringNula(pagamento, "Pagamento");
    }

    protected void validarQuantidade(Integer quantidade){
        if (quantidade == null){
            throw new ValidacaoException("Quantidade não pode ser vazio");
        }

        if(quantidade < 0){
            throw new ValidacaoException("A quantidade precisa ser maior que 0");
        }
    }

    protected void validarStringNula(String string, String campo){
        if(string == null || string.isBlank()){
            throw new ValidacaoException(campo + " Não pode ser vazio");
        }
    }

    protected void validarTamanhoString(String string, String campo, int min, int max){
        if(string.length() < min || string.length() > max){
            throw new ValidacaoException(campo + " deve estar entre " + min + " e " + max + " caracteres");
        }
    }

    protected abstract void validarAtributosEspecificos(Anuncio anuncioValidar);
}

