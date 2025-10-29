package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.Model.DTO.ConferirVaga.retornoConferirVaga;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.MarcarPontoDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.RetornarJornadaDeTrabalhoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.BuscarJornadaDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.criarNotificacaoDTO;
import com.grupo6.ConectaJob.Service.AIService.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserTrabalhadorService {

    @Autowired
    private ContratoService contratoService;

    @Autowired
    JornadaDeTrabalhoService jornadaDeTrabalhoService;

    @Autowired
    NotificacaoService notificacaoService;

    public retornoConferirVaga verificarContrato(MultipartFile contrato, String nomeVaga, String empresaResponsavelCNPJ){
        return contratoService.verificarContrato(contrato, nomeVaga, empresaResponsavelCNPJ);
    }

    public void marcarSaida(MarcarPontoDTO marcarPontoDTO){
        jornadaDeTrabalhoService.marcarSaida(
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

    public boolean aplicarParaVaga(criarNotificacaoDTO criarNotificacaoDTO){
        return notificacaoService.aplicarParaVaga(criarNotificacaoDTO);
    }
}
