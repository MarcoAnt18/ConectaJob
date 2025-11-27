package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.Anunciante.AnuncianteRepository;
import com.grupo6.ConectaJob.Model.DTO.*;
import com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoAnuncianteDTO;
import com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoEmpresaDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.MarcarPontoDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.RetornarJornadaDeTrabalhoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.BuscarJornadaDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.RetornoNotificacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.deletarNotifcacaoDTO;
import com.grupo6.ConectaJob.Model.userEmpresa.EmpresaRepository;
import com.grupo6.ConectaJob.Model.userEmpresa.Empresa;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import com.grupo6.ConectaJob.Model.vaga.vagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired
    private UserGenericRepository UserGenericRepository;

    //-------------------------------------------------
    //Tirar depois
    @Autowired
    private EmpresaRepository empresaRepository;
    //Colocar
    @Autowired
    private AnuncianteRepository anuncianteRepository;
    //--------------------------------------

    @Autowired
    JornadaDeTrabalhoService jornadaDeTrabalhoService;

    @Autowired
    NotificacaoService notificacaoService;

    @Autowired
    private vagaRepository vagaRepository;


    public boolean createEmpresa (Anunciante anunciante){
        var representante = UserGenericRepository.findByCpf(anunciante.getCpfAtrelado());

        if (representante == null){
            throw new notFound("Usuario com este CPF no site não encontrado");
        }

        anuncianteRepository.save(anunciante);
        return true;
    }

    public RetornoAnuncianteDTO buscaEmpresa (String id){

        Anunciante anuncianteRequeirdo = anuncianteRepository.findAnuncianteById(id);

        if (anuncianteRequeirdo == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        Empresa empresa = (Empresa) anuncianteRequeirdo;

        return new RetornoEmpresaDTO(
                empresa.getNomeAnunciante(), empresa.getMeioDeComunicacao(),
                empresa.getFtPerfilLink(), empresa.getCnpjEmpresa(),
                empresa.getSegmento(), empresa.getServicoPrestado(),
                empresa.getAvaliacoesSegundoCargo()
        );
    }

    public boolean editarEmpresa(searchDTO searchId, Anunciante novoAnunciante){
        Anunciante anuncianteParaAtualizar = anuncianteRepository.findAnuncianteById(searchId.cnpj());

        if (anuncianteParaAtualizar == null){
            throw new notFound("Anunciante não encontrado");
        }

        Empresa empresaParaAtualizada = (Empresa) anuncianteParaAtualizar;

        empresaParaAtualizada.atualizarAnunciante(novoAnunciante);

        anuncianteRepository.save(empresaParaAtualizada);

        return true;
    }

    public boolean deletarEmpresa(searchDTO searchCNPJ){
        var anunciante = anuncianteRepository.findAnuncianteById(searchCNPJ.cnpj());

        if (anunciante == null){
            throw new notFound("Empresa com este ID não encontrado");
        }

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
