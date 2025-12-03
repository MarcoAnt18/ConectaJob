package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.Anunciante.*;
import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.AnuncioRepository;
import com.grupo6.ConectaJob.Model.DTO.*;
import com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoAnuncianteDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.MarcarPontoDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.RetornarJornadaDeTrabalhoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.BuscarJornadaDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.RetornoNotificacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.deletarNotifcacaoDTO;
import com.grupo6.ConectaJob.Model.userEmpresa.*;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnuncianteService {
    @Autowired
    private UserGenericRepository userGenericRepository;

    @Autowired
    private ValidarEntradaAnunciante validadorEntrada;

    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    JornadaDeTrabalhoService jornadaDeTrabalhoService;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    NotificacaoService notificacaoService;

    public boolean createAnunciante(Anunciante anunciante){
        validadorEntrada.validarAnunciante(anunciante);

        anuncianteRepository.save(anunciante);

        return true;
    }

    public RetornoAnuncianteDTO buscaAnunciante(String id){

        Anunciante anuncianteRequeirdo = buscarAnuncianteBD(id);

        StrategyRetornoAnuncianteDTO criarDTO = new CriarRetornoEmpresaDTO();

        return criarDTO.CriarRetornoAnuncianteDTO(anuncianteRequeirdo);
    }

    public boolean editarAnunciante(searchDTO searchId, Anunciante novoAnunciante){
        Anunciante anuncianteParaAtualizar = buscarAnuncianteBD(searchId.id());

        StrategyAtualizarAnunciante atualizarAnunciante = new AtualizarEmpresa();

        atualizarAnunciante.atualizar(anuncianteParaAtualizar, novoAnunciante);

        anuncianteRepository.save(anuncianteParaAtualizar);

        return true;
    }

    public boolean deletarAnunciante(searchDTO searchCNPJ){
        var anunciante = buscarAnuncianteBD(searchCNPJ.id());

        //Deleta anúncios do anunciante
        List<Anuncio> anuncios = anuncioRepository.findAll();
        for(Anuncio anuncio : anuncios){
            if(Objects.equals(anuncio.getAnuncianteResponsavelId(), searchCNPJ.id())) {
                anuncioRepository.delete(anuncio);
            }
        }

        anuncianteRepository.delete(anunciante);

        return true;
    }

    public Anunciante buscarAnuncianteBD(String id){
        Anunciante anuncianteBusca = anuncianteRepository.findAnuncianteById(id);

        if (anuncianteBusca == null){
            throw new notFound("Anunciante não encontrado");
        }

        return anuncianteBusca;
    }

    public void marcarEntrada(MarcarPontoDTO marcarPontoDTO){
        jornadaDeTrabalhoService.marcarEntrada(
                marcarPontoDTO.trabalhadorCPF(),
                marcarPontoDTO.empresaResponsavelCPNJ(),
                marcarPontoDTO.nomeVaga()
        );
    }

    public RetornarJornadaDeTrabalhoDTO buscarJornadaDeTrabalho(BuscarJornadaDTO buscarJornadaDTO){
        return jornadaDeTrabalhoService.lerJornadaDeTrabalho(
                buscarJornadaDTO.trabalhadorCPF(),
                buscarJornadaDTO.empresaResponsavelCPNJ(),
                buscarJornadaDTO.nomeVaga()
        );
    }

    public RetornoNotificacaoDTO buscarNotificacoes(searchDTO searchCNPJ){
        return notificacaoService.buscarNotificacoes(searchCNPJ);
    }

    public boolean deletarNotificacao(deletarNotifcacaoDTO deletarNotifcacaoDTO){
        return notificacaoService.deletarNotificacao(deletarNotifcacaoDTO);
    }
}
