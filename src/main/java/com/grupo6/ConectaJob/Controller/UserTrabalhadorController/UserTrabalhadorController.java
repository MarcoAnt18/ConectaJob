package com.grupo6.ConectaJob.Controller.UserTrabalhadorController;


import com.grupo6.ConectaJob.Model.DTO.ConferirVaga.retornoConferirVaga;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.MarcarPontoDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.RetornarJornadaDeTrabalhoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.BuscarJornadaDTO;
import com.grupo6.ConectaJob.Service.UserTrabalhadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/trabalhador")
public class UserTrabalhadorController {

    @Autowired
    private UserTrabalhadorService userTrabalhadorService;

    @GetMapping("/verificarContrato")
    public ResponseEntity<retornoConferirVaga> verificarContrato(
            @RequestParam("file") MultipartFile contrato,
            @RequestParam("nomeVaga") String nomeVaga,
            @RequestParam("empresaResponsavelCNPJ") String empresaResponsavelCNPJ
    ){
        return ResponseEntity.ok(userTrabalhadorService.verificarContrato(contrato, nomeVaga, empresaResponsavelCNPJ));
    }

    @PostMapping("/marcarSaida")
    public boolean marcarSaida(@RequestBody MarcarPontoDTO marcarPontoDTO){
        userTrabalhadorService.marcarSaida(marcarPontoDTO);
        return true;
    }

    @GetMapping("/buscarJornada")
    public ResponseEntity<RetornarJornadaDeTrabalhoDTO> buscarJornadaDeTrabalho(@RequestBody BuscarJornadaDTO buscarJornadaDTO){
        return ResponseEntity.ok(userTrabalhadorService.buscarJornadaDeTrabalho(buscarJornadaDTO));
    }
}
