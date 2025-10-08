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
    @Autowired
    private vagaRepository vagaRepository;
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

    public boolean createVaga (criarVagaDTO criarVagaDTO){
        var empresaResponvalel = empresaRepository.findEmpresaByCNPJ(criarVagaDTO.empresaReponsavelCNPJ());

        if (empresaResponvalel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        //Verifica se a vaga já foi cadastrada na empresa
        vagaTrabalho VagaEncontrada = buscarVagaTrabalho(criarVagaDTO.servicoPrestadoNaOcasiao().getNomeServico(),
                criarVagaDTO.empresaReponsavelCNPJ());

        if(VagaEncontrada != null) {
            throw new DuplicateEntityException("Vaga já cadastrada");
        }

        var vaga  = new vagaTrabalho
                        (criarVagaDTO.empresaReponsavelCNPJ(),
                        criarVagaDTO.servicoPrestadoNaOcasiao(),
                        criarVagaDTO.cargo(),criarVagaDTO.jornadaAmpla(),
                        criarVagaDTO.jornandaDetalhada(),
                        criarVagaDTO.numeroVagas(),
                        criarVagaDTO.pagamento(),
                        criarVagaDTO.meioDeComunicacao(),
                        criarVagaDTO.equipamentoDeSeguranca());

        vagaRepository.save(vaga);

        return true;
    }

    public retornoVagaExistente BuscarVagaPorNome(searchVaga searchVaga){

        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(searchVaga.empresaResponsavelCNPJ());

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        vagaTrabalho VagaEncontrada = buscarVagaTrabalho(searchVaga.nomeVaga(), searchVaga.empresaResponsavelCNPJ());

        if(VagaEncontrada == null){
            throw new notFound("Vaga com esse nome não encontrada na empresa");
        }

        return new retornoVagaExistente(VagaEncontrada.getEmpresaReponsavelCNPJ(),
                VagaEncontrada.getServicoPrestadoNaOcasiao(),
                VagaEncontrada.getCargoIndividuo(),
                VagaEncontrada.getJornadaAmpla(),
                VagaEncontrada.getJornandaDetalhada(),
                VagaEncontrada.getNumeroDeVagasAbertas(),
                VagaEncontrada.getPagamento(),
                VagaEncontrada.getMeioDeComunicacao(),
                VagaEncontrada.getEquipamentoDeSeguranca());
    }

    //Usado para procurar uma vaga no banco de dados pelo nome da vaga e CNPJ da empresa vinculada
    public vagaTrabalho buscarVagaTrabalho(String nomeVaga, String CNPJ) {
        List<vagaTrabalho> vagas = vagaRepository.findAll();
        vagaTrabalho VagaEncontrada = null;

        //Percorre as vagas cadastradas buscando pelo nome e CNPJ informado
        for(vagaTrabalho vaga : vagas){
            if(Objects.equals(vaga.getServicoPrestadoNaOcasiao().getNomeServico(), nomeVaga) &&
                    Objects.equals(vaga.getEmpresaReponsavelCNPJ(), CNPJ)) {
                VagaEncontrada = vaga;
            }
        }

        return VagaEncontrada;
    }

    public boolean deleteVaga (String nomeVaga, String CNPJ){
        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(CNPJ);

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        vagaTrabalho VagaEncontrada = buscarVagaTrabalho(nomeVaga, CNPJ);

        if(VagaEncontrada == null){
            throw new notFound("Vaga com esse nome não encontrada na empresa");
        }

        vagaRepository.delete(VagaEncontrada);

        return true;
    }

    public boolean editVaga(searchVaga searchVaga, novaVagaDTO novaVagaDTO){
        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(searchVaga.empresaResponsavelCNPJ());

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        vagaTrabalho VagaAntiga = buscarVagaTrabalho(searchVaga.nomeVaga(), searchVaga.empresaResponsavelCNPJ());

        if(VagaAntiga == null){
            throw new notFound("Vaga com esse nome não encontrada na empresa");
        }

        //Cria a nova vaga usando o construtor
        var NovaVaga = new vagaTrabalho(
                (novaVagaDTO.empresaReponsavelCNPJ() == null) ? VagaAntiga.getEmpresaReponsavelCNPJ() : novaVagaDTO.empresaReponsavelCNPJ(),
                (novaVagaDTO.servicoPrestadoNaOcasiao() == null) ? VagaAntiga.getServicoPrestadoNaOcasiao() : novaVagaDTO.servicoPrestadoNaOcasiao(),
                (novaVagaDTO.cargo() == null) ? VagaAntiga.getCargoIndividuo() : novaVagaDTO.cargo(),
                (novaVagaDTO.jornadaAmpla() == null) ? VagaAntiga.getJornadaAmpla() : novaVagaDTO.jornadaAmpla(),
                (novaVagaDTO.jornandaDetalhada() == null) ? VagaAntiga.getJornandaDetalhada() : novaVagaDTO.jornandaDetalhada(),
                (novaVagaDTO.numeroVagas() == null) ? VagaAntiga.getNumeroDeVagasAbertas() : novaVagaDTO.numeroVagas(),
                (novaVagaDTO.pagamento() == null) ? VagaAntiga.getPagamento() : novaVagaDTO.pagamento(),
                (novaVagaDTO.meioDeComunicacao() == null) ? VagaAntiga.getMeioDeComunicacao() : novaVagaDTO.meioDeComunicacao(),
                (novaVagaDTO.equipamentoDeSeguranca() == null) ? VagaAntiga.getEquipamentoDeSeguranca() : novaVagaDTO.equipamentoDeSeguranca()
        );

        //SetID
        NovaVaga.setVagaId(VagaAntiga.getVagaId());

        //Adiciona ao banco de dados
        vagaRepository.save(NovaVaga);

        return true;
    }

    public List<vagaTrabalho> buscaTodasVagas (){
        return vagaRepository.findAll();
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
