package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.Anunciante.*;
import com.grupo6.ConectaJob.Model.DTO.*;
import com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoAnuncianteDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.MarcarPontoDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.RetornarJornadaDeTrabalhoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.BuscarJornadaDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.RetornoNotificacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.deletarNotifcacaoDTO;
import com.grupo6.ConectaJob.Model.userEmpresa.*;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import com.grupo6.ConectaJob.Model.vaga.vagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    NotificacaoService notificacaoService;

    public boolean createAnunciante(Anunciante anunciante){
        validadorEntrada.validarAnunciante(anunciante);

        anuncianteRepository.save(anunciante);

        return true;
    }

    public RetornoAnuncianteDTO buscaAnunciante(String id){

        Anunciante anuncianteRequeirdo = buscarAnuncianteBD(id);

        StrategyRetornoAnuncianteDTO criarDTO = new RetornoEmpresaDTO();

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

        //AJEITAR COM ANUNCIOS DEPOIS
        /*//Deleta Vagas da empresa
        List<vagaTrabalho> vagas = vagaRepository.findAll();
        for(vagaTrabalho vaga : vagas){
            if(Objects.equals(vaga.getEmpresaReponsavelCNPJ(), searchCNPJ.cnpj())) {
                vagaRepository.delete(vaga);
            }
        }*/

        //Deleta anunciante
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
