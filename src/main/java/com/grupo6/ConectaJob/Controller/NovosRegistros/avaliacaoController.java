package com.grupo6.ConectaJob.Controller.NovosRegistros;

import com.grupo6.ConectaJob.Model.DTO.createTrabalhadorUserDTO;
import com.grupo6.ConectaJob.Model.DTO.loginTrabalhadorDTO;
import com.grupo6.ConectaJob.Service.userTrabalhadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/userTrabalhador")
public class avaliacaoController {
    @Autowired
    private userTrabalhadorService trabalhadorservice;

    @PostMapping("/criarUserTrabalhador")
    public boolean createtrabalhador(@RequestBody createTrabalhadorUserDTO trabalhadorDTO){
        trabalhadorservice.createtrabalhador(trabalhadorDTO);
        return true;
    }

    @PostMapping("/deletarUserTrabalhador")
    public boolean deletetrabalhador(@RequestBody String cpf){
        trabalhadorservice.deletetrabalhador(cpf);
        return true;
    }

    @GetMapping("/BuscarTrabalhador")
    public ResponseEntity<createTrabalhadorUserDTO> buscarusertrabalhador(@RequestBody String cpf){
        return ResponseEntity.ok(trabalhadorservice.buscaruserTrabalhador(cpf));
    }

    @PutMapping("/EditarUserTrabalhador")
    public boolean editartrabalhador(@RequestBody createTrabalhadorUserDTO trabalhadorDTO, String cpf){
        trabalhadorservice.edittrabalhador(cpf, trabalhadorDTO);
        return true;
    }
}
