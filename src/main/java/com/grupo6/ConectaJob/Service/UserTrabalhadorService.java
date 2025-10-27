package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.Model.DTO.ConferirVaga.retornoConferirVaga;
import com.grupo6.ConectaJob.Service.AIService.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserTrabalhadorService {

    @Autowired
    private ContratoService contratoService;

    public retornoConferirVaga verificarContrato(MultipartFile contrato, String nomeVaga, String empresaResponsavelCNPJ) throws Exception{
        return contratoService.verificarContrato(contrato, nomeVaga, empresaResponsavelCNPJ);
    }

}
