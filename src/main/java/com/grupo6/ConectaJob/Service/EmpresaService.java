package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.DuplicateEntityException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.*;
import com.grupo6.ConectaJob.Model.notificacao.Notificacao;
import com.grupo6.ConectaJob.Model.userEmpresa.EmpresaRepository;
import com.grupo6.ConectaJob.Model.userEmpresa.empresa;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import com.grupo6.ConectaJob.Model.vaga.vagaRepository;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmpresaService {
    @Autowired
    private UserGenericRepository UserGenericRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    //-----------------------------------------------------
    //APAGAR DEPOIS, SUBISTITUIR POR SERVICE DE VAGA
    @Autowired
    private vagaRepository vagaRepository;
    //------------------------------------------------------


    public boolean createEmpresa (createEmpresaDTO crateEmpresaDTO){
        var representante = UserGenericRepository.findByCpf(crateEmpresaDTO.CPFatrelado());

        if (representante == null){
            throw new notFound("Usuario com este CPF no site não encontrado");
        }

        var novaEmpresa = new empresa(crateEmpresaDTO.CPFatrelado(), crateEmpresaDTO.CNPJ(),
        crateEmpresaDTO.nomeEmpresa(),crateEmpresaDTO.segmento(), crateEmpresaDTO.servicoPrestadoList(),
        crateEmpresaDTO.meioDeComunicacao());

        empresaRepository.save(novaEmpresa);
        return true;
    }

    public retornoEmpresaExiste buscaEmpresa (String cnpj){

        var empresaRequerida = empresaRepository.findEmpresaByCNPJ(cnpj);

        if (empresaRequerida == null){
            throw new notFound("Não existe empresa com CNPJ neste site !");
        }

        System.out.println(empresaRequerida);

        return new retornoEmpresaExiste(empresaRequerida.getCNPJ(),
                empresaRequerida.getNomeEmpresa(), empresaRequerida.getSegmento(),
                empresaRequerida.getFtPerfilLink(),empresaRequerida.getServicoPrestado(),
                empresaRequerida.getMeioDeComunicacao());
    }

    public boolean editarEmpresa(searchDTO searchCNPJ, createEmpresaDTO novaEmpresa){

        var empresaAntiga = empresaRepository.findEmpresaByCNPJ(searchCNPJ.cnpj());

        if (empresaAntiga == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        var EmpresaEditada = new empresa(
                (novaEmpresa.CPFatrelado() == null) ? empresaAntiga.getCPFatrelado() : novaEmpresa.CPFatrelado(),
                (novaEmpresa.CNPJ() == null) ? empresaAntiga.getCNPJ() : novaEmpresa.CNPJ(),
                (novaEmpresa.nomeEmpresa() == null) ? empresaAntiga.getNomeEmpresa() : novaEmpresa.nomeEmpresa(),
                (novaEmpresa.segmento() == null) ? empresaAntiga.getSegmento() : novaEmpresa.segmento(),
                (novaEmpresa.servicoPrestadoList() == null) ? empresaAntiga.getServicoPrestado() : novaEmpresa.servicoPrestadoList(),
                (novaEmpresa.meioDeComunicacao() == null) ? empresaAntiga.getMeioDeComunicacao() : novaEmpresa.meioDeComunicacao()
        );

        EmpresaEditada.setId(empresaAntiga.getId());

        empresaRepository.save(EmpresaEditada);

        return true;
    }

    public boolean deletarEmpresa(searchDTO searchCNPJ){
        var empresa = empresaRepository.findEmpresaByCNPJ(searchCNPJ.cnpj());

        if (empresa == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        //Deleta Vagas da empresa
        List<vagaTrabalho> vagas = vagaRepository.findAll();
        for(vagaTrabalho vaga : vagas){
            if(Objects.equals(vaga.getEmpresaReponsavelCNPJ(), searchCNPJ.cnpj())) {
                vagaRepository.delete(vaga);
            }
        }

        //Deleta empresa
        empresaRepository.delete(empresa);

        return true;
    }


    public boolean apagarNotificacao(String nomeUsuario, String nomeVaga, String empresaCNPJ){

        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(empresaCNPJ);

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        List<Notificacao> notificacoes = empresaResponsavel.getNotificacoes();

        for(Notificacao notificacao : notificacoes){
            if(Objects.equals(notificacao.getUsuario().getNome(), nomeUsuario) && Objects.equals(notificacao.getVagaTrabalho().getCargoIndividuo().getNomeCargo(), nomeVaga)){
                empresaResponsavel.deleteNotificacao(notificacao);
                break;
            }
        }

        empresaRepository.save(empresaResponsavel);

        return true;
    }

    public List<Notificacao> buscarNOtificacoes(searchDTO searchDTO){
        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(searchDTO.cnpj());

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        return empresaResponsavel.getNotificacoes();
    }
}
