package com.grupo6.ConectaJob.Model.vaga;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.ValidacaoException;
import com.grupo6.ConectaJob.Model.Anuncio.Anuncio;
import com.grupo6.ConectaJob.Model.Anuncio.ValidarEntradaAnuncio;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempo;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempoSalvosubCategorias;
import com.grupo6.ConectaJob.Model.cargo.Cargo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ValidarEntradaVaga")
public class ValidarEntradaVaga extends ValidarEntradaAnuncio {

    public void validarAtributosEspecificos(Anuncio anuncioValidar){
        VagaTrabalho vagaValidar = (VagaTrabalho) anuncioValidar;

        validarCargo(vagaValidar.getCargo());
        validarEquipamentoDeSeguranca(vagaValidar.getEquipamentoDeSeguranca());
        validarJornadaAmpla(vagaValidar.getJornadaAmpla());
        validarJornadaDetalhada(vagaValidar.getJornandaDetalhada());
    }

    private void validarCargo(Cargo cargo){
        if (cargo == null){
            throw new ValidacaoException("O Cargo não pode ser nulo");
        }

        validarStringNula(cargo.getNomeCargo(), "Nome do Cargo");
        validarStringNula(cargo.getVisaoCargo(), "Visão do Cargo");

        validarTamanhoString(cargo.getNomeCargo(), "Nome do Cargo", 3, 100);
        validarTamanhoString(cargo.getVisaoCargo(), "Visão do Cargo", 5, 500);
    }

    private void validarEquipamentoDeSeguranca(String equipamentoDeSeguranca){
        validarStringNula(equipamentoDeSeguranca, "Equipamento de Segurança");
        validarTamanhoString(equipamentoDeSeguranca, "Equipamento de Segurança", 3, 200);
    }

    private void validarJornadaAmpla(IntervaloTempo jornadaAmpla){
        validarIntervaloTempo(jornadaAmpla);
    }

    private void validarJornadaDetalhada(IntervaloTempoSalvosubCategorias jornadaDetalhada){
        if (jornadaDetalhada == null){
            throw new ValidacaoException("A jornada Detalhada não pode ser nula");
        }

        List<IntervaloTempo> descanso = jornadaDetalhada.getJornadaCdescanco();

        if (descanso.size() != 3) {
            throw new ValidacaoException("A jornada de descanso deve conter exatamente 3 intervalos.");
        }

        validarIntervaloTempo(descanso.get(0));
        validarIntervaloTempo(descanso.get(1));
        validarJornadaAmpla(descanso.get(2));

        if (descanso.get(0).getSaida().isAfter(descanso.get(1).getEntrada())) {
            throw new ValidacaoException("O pré-descanso deve terminar antes do descanso iniciar.");
        }

        if (descanso.get(1).getSaida().isAfter(descanso.get(2).getEntrada())) {
            throw new ValidacaoException("O descanso deve terminar antes do pós-descanso iniciar.");
        }
    }

    public void validarIntervaloTempo(IntervaloTempo intervaloDeTempo){
        if (intervaloDeTempo == null){
            throw new ValidacaoException("Jornada ampla não pode ser nulo");
        }

        if (intervaloDeTempo.getEntrada() == null || intervaloDeTempo.getSaida() == null) {
            throw new IllegalArgumentException("Entrada e saída de jornada ampla são obrigatórias.");
        }

        if (intervaloDeTempo.getEntrada().isAfter(intervaloDeTempo.getSaida())){
            throw new ValidacaoException("Na jornada ampla a hora de entrada deve ser antes da hora de saída");
        }
    }
}
