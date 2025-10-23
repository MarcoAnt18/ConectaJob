package com.grupo6.ConectaJob.Controller.UserTrabalhadorController;

import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Service.AIService.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Trabalhador")
public class UserTrabalhadorController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping("/VerificarContrato")
    public String verificarContrato(@RequestBody searchVaga searchVaga) throws Exception{
        return contratoService.getRespostaIA(searchVaga);
    }
}
