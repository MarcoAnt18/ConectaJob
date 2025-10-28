package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.DuplicateEntityException;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.RetornarJornadaDeTrabalhoDTO;
import com.grupo6.ConectaJob.Model.JornadaDeTrabalho.JornadaDeTrabalho;
import com.grupo6.ConectaJob.Model.JornadaDeTrabalho.JornadaDeTrabalhoRepository;
import com.grupo6.ConectaJob.Model.userEmpresa.EmpresaRepository;
import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaDeTrabalhoService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UserGenericRepository UserGenericRepository;

    @Autowired
    private JornadaDeTrabalhoRepository jornadaDeTrabalhoRepository;

    @Autowired
    private VagaService vagaService;

    public void marcarEntrada(String trabalhadorCPF, String empresaResponsavelCPNJ, String nomeVaga){
        verificarEntradaJornadaDeTrabalho(trabalhadorCPF, empresaResponsavelCPNJ, nomeVaga);

        JornadaDeTrabalho jornadaDeTrabalhoProcurada = buscarJornadaDeTrabalho(trabalhadorCPF,empresaResponsavelCPNJ, nomeVaga);

        JornadaDeTrabalho novaJornada;

        if(jornadaDeTrabalhoProcurada == null){
            novaJornada = new JornadaDeTrabalho(trabalhadorCPF, empresaResponsavelCPNJ, nomeVaga);
        }
        else{
            novaJornada = jornadaDeTrabalhoProcurada;
        }

        boolean resultado = novaJornada.marcarEntrada();

        if(resultado == false){
            throw new DuplicateEntityException("Ponto já registrado hoje");
        }

        jornadaDeTrabalhoRepository.save(novaJornada);
    }

    public void marcarSaida(String trabalhadorCPF, String empresaResponsavelCPNJ, String nomeVaga){

        verificarEntradaJornadaDeTrabalho(trabalhadorCPF, empresaResponsavelCPNJ, nomeVaga);

        JornadaDeTrabalho jornadaDeTrabalhoProcurada = buscarJornadaDeTrabalho(trabalhadorCPF,empresaResponsavelCPNJ, nomeVaga);

        JornadaDeTrabalho novaJornada;

        if(jornadaDeTrabalhoProcurada == null){
            novaJornada = new JornadaDeTrabalho(trabalhadorCPF, empresaResponsavelCPNJ, nomeVaga);
        }
        else{
            novaJornada = jornadaDeTrabalhoProcurada;
        }

        boolean resultado = novaJornada.marcarSaida();

        if(resultado == false){
            throw new DuplicateEntityException("Ponto já registrado hoje");
        }

        jornadaDeTrabalhoRepository.save(novaJornada);
    }

    public RetornarJornadaDeTrabalhoDTO lerJornadaDeTrabalho(String trabalhadorCPF, String empresaResponsavelCPNJ, String nomeVaga){

        JornadaDeTrabalho jornadaDeTrabalhoInformada = buscarJornadaDeTrabalho(trabalhadorCPF, empresaResponsavelCPNJ, nomeVaga);

        if(jornadaDeTrabalhoInformada == null){
            throw new notFound("Jornada de trabalho não encontrada");
        }

        return new RetornarJornadaDeTrabalhoDTO(jornadaDeTrabalhoInformada.getRegistroDePontos());
    }

    public void verificarEntradaJornadaDeTrabalho(String trabalahdorCPF, String empresaResponsavelCPNJ, String nomeVaga){
        var empresaResponsavel = empresaRepository.findEmpresaByCNPJ(empresaResponsavelCPNJ);

        if (empresaResponsavel == null){
            throw new notFound("Empresa com este CNPJ no site não encontrado");
        }

        var trabalhadorInformado = UserGenericRepository.findByCpf(trabalahdorCPF);

        if (trabalhadorInformado == null){
            throw new notFound("Trabalhador com este CPF no site não encontrado");
        }

        vagaTrabalho vagaInformada = vagaService.buscarVagaTrabalho(nomeVaga, empresaResponsavelCPNJ);

        if(vagaInformada == null){
            throw new notFound("Vaga com esse nome não encontrada na empresa");
        }
    }

    public JornadaDeTrabalho buscarJornadaDeTrabalho(String trabalhadorCPF, String empresaResponsavelCPNJ, String nomeVaga){
        JornadaDeTrabalho jornadaEncontrada = null;

        List <JornadaDeTrabalho> jornadas = jornadaDeTrabalhoRepository.findByEmpresaAtreladaCNPJ(empresaResponsavelCPNJ);

        for(JornadaDeTrabalho jornada : jornadas){
            if(jornada.getUsuarioAtreladoCPF().equals(trabalhadorCPF) && jornada.getVagaAtrelada().equals(nomeVaga)){
                jornadaEncontrada = jornada;
            }
        }

        return jornadaEncontrada;
    }
}
