package com.grupo6.ConectaJob.Controller.NovosRegistros;

import com.grupo6.ConectaJob.Service.userTrabalhadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userTrabalhador")
public class avaliacaoController {
    @Autowired
    private userTrabalhadorService trabalhadorservice;


}
