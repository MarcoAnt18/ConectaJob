package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.Controller.VagaController.VagaController;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.DuplicateEntityException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.Anunciante.AnuncianteRepository;
import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.AnuncioRepository;
import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoVagaDTO;
import com.grupo6.ConectaJob.Model.DTO.SearchAnuncioDTO;
import com.grupo6.ConectaJob.Model.userEmpresa.EmpresaRepository;
import com.grupo6.ConectaJob.Model.vaga.vagaRepository;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import jakarta.validation.OverridesAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AnuncioService {
    //Subistituir ----------------------------
    @Autowired
    private vagaRepository vagaRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;
    //------------------------------------------

    //Subistituir ----------------------------
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;
    //----------------------------------------------

    public boolean createAnuncio(Anuncio anuncio){
        //Vira validação ------------------------------------------------
        var anuncianteResponvalel = anuncianteRepository.findAnuncianteById(anuncio.getAnuncianteResponsavelId());

        if (anuncianteResponvalel == null){
            throw new notFound("Anunciante Atrelado com este ID não encontrado");
        }

        //Verifica se a vaga já foi cadastrada na empresa
        Anuncio anuncioEncontrado = buscarAnuncioBD(anuncio.getNomeAnuncio(), anuncio.getAnuncianteResponsavelId());

        if(anuncioEncontrado != null) {
            throw new DuplicateEntityException("Anuncio já cadastrado");
        }
        //------------------------------------------------------------------

        anuncioRepository.save(anuncio);
        return true;
    }

    public RetornoAnuncioDTO BuscarAnuncio(SearchAnuncioDTO searchAnuncio){
        Anuncio anuncioEncontrado = buscarAnuncioBD(searchAnuncio.nomeAnuncio(),searchAnuncio.anuncianteResponsavelId());

        //Strategy----------------------------------------
        VagaTrabalho vagaEncontrada = (VagaTrabalho) anuncioEncontrado;

        return new RetornoVagaDTO(
                vagaEncontrada.getAnuncianteResponsavelId(),
                vagaEncontrada.getNomeAnuncio(),
                vagaEncontrada.getDescricaoAnuncio(),
                vagaEncontrada.getMeioDeComunicacao(),
                vagaEncontrada.getPagamento(),
                vagaEncontrada.getQuantidade(),
                vagaEncontrada.getCargo(),
                vagaEncontrada.getEquipamentoDeSeguranca(),
                vagaEncontrada.getJornadaAmpla(),
                vagaEncontrada.getJornandaDetalhada()
        );
        //----------------------------------------------------
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
        Anuncio anuncioAntigo = buscarAnuncioBD(searchAnuncio.nomeAnuncio(),searchAnuncio.anuncianteResponsavelId());


        //Strategy-----------------------------
        VagaTrabalho vagaParaAtualizar = (VagaTrabalho) anuncioAntigo;

        vagaParaAtualizar.atualizarAnuncio(novoAnuncio);
        //------------------------------------

        //Adiciona ao banco de dados
        vagaRepository.save(vagaParaAtualizar);

        return true;
    }

    /*public List<Anuncio> buscaTodosAnuncios(){
        List<Anuncio> anuncios = anuncioRepository.findAll();

        //Colocar no Strategy
        List<VagaTrabalho> vagas = new ArrayList<>();

        for(Anuncio anuncio : anuncios){
            vagas.add((VagaTrabalho) anuncio);
        }

        return vagas;
    }*/

}
