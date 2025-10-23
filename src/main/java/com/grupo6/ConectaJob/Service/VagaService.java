package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.DuplicateEntityException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.criarVagaDTO;
import com.grupo6.ConectaJob.Model.DTO.novaVagaDTO;
import com.grupo6.ConectaJob.Model.DTO.retornoVagaExistente;
import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Model.userEmpresa.EmpresaRepository;
import com.grupo6.ConectaJob.Model.vaga.vagaRepository;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VagaService {
    @Autowired
    private vagaRepository vagaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

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

}
