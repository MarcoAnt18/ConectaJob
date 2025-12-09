package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.Anunciante.AnuncianteRepository;
import com.grupo6.ConectaJob.Model.Anuncio.*;
import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.DTO.SearchAnuncioDTO;
import com.grupo6.ConectaJob.Model.vaga.AtualizarVaga;
import com.grupo6.ConectaJob.Model.vaga.CriarRetornoVagaDTO;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    private ValidarEntradaAnuncio validarEntradaAnuncio;

    public boolean createAnuncio(Anuncio anuncio){
        validarEntradaAnuncio.validarEntradaAnuncio(anuncio);

        anuncioRepository.save(anuncio);

        return true;
    }

    public RetornoAnuncioDTO BuscarAnuncio(SearchAnuncioDTO searchAnuncio){
        Anuncio anuncioEncontrado = buscarAnuncioBD(searchAnuncio.nomeAnuncio(),searchAnuncio.anuncianteResponsavelId());

        StrategyRetornoAnuncioDTO criarDTO = new CriarRetornoVagaDTO();

        return criarDTO.criarRetornoAnuncioDTO(anuncioEncontrado);
    }

    //Usado para procurar um anúncio no banco de dados pelo nome do anúncio e ID do anunciante responsável
    public Anuncio buscarAnuncioBD(String nomeAnuncio, String anuncianteID) {
        //Verifica se o anunciante
        var anuncianteResponsavel = anuncianteRepository.findAnuncianteById(anuncianteID);

        if (anuncianteResponsavel == null){
            throw new notFound("Anunciante com este ID não encontrado");
        }

        //Procura pelo anúncio
        List<Anuncio> anuncios = anuncioRepository.findAll();

        //Percorre os anúncios cadastrados buscando pelo nome e ID do anunciante informado
        for(Anuncio anuncio : anuncios){
            if(Objects.equals(anuncio.getNomeAnuncio(), nomeAnuncio) &&
               Objects.equals(anuncio.getAnuncianteResponsavelId(), anuncianteID)){
                return anuncio;
            }
        }

        throw new notFound("Anuncio come esse nome não encontrado no Anunciante");
    }

    public boolean deletarAnuncio(String nomeVaga, String anuncianteResponsavelId){
        Anuncio anuncioEncontrado = buscarAnuncioBD(nomeVaga, anuncianteResponsavelId);

        anuncioRepository.delete(anuncioEncontrado);

        return true;
    }

    public boolean editarAnuncio(SearchAnuncioDTO searchAnuncio, Anuncio novoAnuncio){
        Anuncio anuncioParaAtualizar = buscarAnuncioBD(searchAnuncio.nomeAnuncio(),searchAnuncio.anuncianteResponsavelId());

        StrategyAtualizarAnuncio atualizarAnuncio = new AtualizarVaga();

        atualizarAnuncio.atualizar(anuncioParaAtualizar, novoAnuncio);

        anuncioRepository.save(anuncioParaAtualizar);

        return true;
    }
}
