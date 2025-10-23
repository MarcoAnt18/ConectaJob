package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Service.AIService.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTrabalhadorService {

    @Autowired
    private ContratoService contratoService;

    String verificarContrato(searchVaga searchVaga) throws Exception{
        return contratoService.getRespostaIA(searchVaga);
    }

}
