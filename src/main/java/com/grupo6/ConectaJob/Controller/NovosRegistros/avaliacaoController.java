package com.grupo6.ConectaJob.Controller.NovosRegistros;

import com.grupo6.ConectaJob.Model.DTO.*;
import com.grupo6.ConectaJob.Service.AvaliacoesService;
import com.grupo6.ConectaJob.Service.userTrabalhadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/userTrabalhador")
public class avaliacaoController {
    @Autowired
    private userTrabalhadorService trabalhadorservice;
    private AvaliacoesService avaliacaoservice;

    @PostMapping("/criarUserTrabalhador")
    public boolean createtrabalhador(@RequestBody createTrabalhadorUserDTO trabalhadorDTO){
        trabalhadorservice.createtrabalhador(trabalhadorDTO);
        return true;
    }

    @PostMapping("/criaravaliacao")
    public boolean criaravaliacao(@RequestBody createavaliacaoDTO avaliacaoDTO, returnTrabalhadorDTO trabalhadorDTO){
        avaliacaoservice.criaravaliacao(avaliacaoDTO, trabalhadorDTO);
        return true;
    }

    @PostMapping("/deletarUserTrabalhador")
    public boolean deletetrabalhador(@RequestBody String cpf){
        trabalhadorservice.deletetrabalhador(cpf);
        return true;
    }

    @PostMapping("/deleteavaliacao")
    public boolean deleteavaliacao(returnTrabalhadorDTO trabalhadorDTO, String avaliadordel){
        avaliacaoservice.deleteavaliacao(trabalhadorDTO, avaliadordel);
        return true;
    }

    @GetMapping("/BuscarTrabalhador")
    public ResponseEntity<createTrabalhadorUserDTO> buscarusertrabalhador(@RequestBody String cpf){
        return ResponseEntity.ok(trabalhadorservice.buscaruserTrabalhador(cpf));
    }

    @GetMapping("/buscaravaliacao")
    public returnavaliacao buscaravaliacao(@RequestBody String CNPJ){
        return avaliacaoservice.buscaravaliacao(CNPJ);
    }

    @PutMapping("/EditarUserTrabalhador")
    public boolean editartrabalhador(@RequestBody createTrabalhadorUserDTO trabalhadorDTO, String cpf){
        trabalhadorservice.edittrabalhador(cpf, trabalhadorDTO);
        return true;
    }

    @PutMapping("/editaravaliacao")
    public Boolean editaravaliacao(@RequestBody String CPF, novaAvaliacaoDTO novaAvaliacao){
        avaliacaoservice.editavaliacao(CPF, novaAvaliacao);
        return true;
    }


}
