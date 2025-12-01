package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.DuplicateEntityException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.Anunciante.AnuncianteRepository;
import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.AnuncioRepository;
import com.grupo6.ConectaJob.Model.userEmpresa.EmpresaRepository;
import com.grupo6.ConectaJob.Model.vaga.vagaRepository;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new DuplicateEntityException("Vaga já cadastrada");
        }
        //------------------------------------------------------------------

        anuncioRepository.save(anuncio);
        return true;
    }

    /*public retornoVagaExistente BuscarAnuncio(searchVaga searchVaga){

        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(searchVaga.empresaResponsavelCNPJ());

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        vagaTrabalho VagaEncontrada = buscarAnuncioBD(searchVaga.nomeVaga(), searchVaga.empresaResponsavelCNPJ());

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


    }*/

    //Usado para procurar um anúncio no banco de dados pelo nome do anúncio e ID do anunciante responsável
    public Anuncio buscarAnuncioBD(String nomeAnuncio, String anuncianteID) {
        List<Anuncio> anuncios = anuncioRepository.findAll();

        //Percorre os anúncios cadastrados buscando pelo nome e ID do anunciante informado
        for(Anuncio anuncio : anuncios){
            if(Objects.equals(anuncio.getNomeAnuncio(), nomeAnuncio) &&
               Objects.equals(anuncio.getAnuncianteResponsavelId(), anuncianteID)){
                return anuncio;
            }
        }

        return null;
    }

    /*public boolean deletarAnuncio(String nomeVaga, String CNPJ){
        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(CNPJ);

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        vagaTrabalho VagaEncontrada = buscarAnuncioBD(nomeVaga, CNPJ);

        if(VagaEncontrada == null){
            throw new notFound("Vaga com esse nome não encontrada na empresa");
        }

        vagaRepository.delete(VagaEncontrada);

        return true;
    }*/

    /*public boolean editarAnuncio(searchVaga searchVaga, novaVagaDTO novaVagaDTO){
        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(searchVaga.empresaResponsavelCNPJ());

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        vagaTrabalho VagaAntiga = buscarAnuncioBD(searchVaga.nomeVaga(), searchVaga.empresaResponsavelCNPJ());

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
    }*/

    public List<VagaTrabalho> buscaTodosAnuncios(){
        return vagaRepository.findAll();
    }

}
