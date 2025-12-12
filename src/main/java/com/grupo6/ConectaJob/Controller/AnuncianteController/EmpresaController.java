package com.grupo6.ConectaJob.Controller.AnuncianteController;

import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.MarcarPontoDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.RetornarJornadaDeTrabalhoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.BuscarJornadaDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.RetornoNotificacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.deletarNotifcacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.editEmpresaDTO;
import com.grupo6.ConectaJob.Model.DTO.searchDTO;
import com.grupo6.ConectaJob.Model.userEmpresa.Empresa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController extends AnuncianteController {

    @PostMapping("/criarEmpresa")
    public boolean criarEmpresa (@RequestBody Empresa empresa){
        anuncianteService.createAnunciante(empresa);
        return true;
    }

    @PutMapping("/editarEmpresa")
    public boolean editarEmpresa(@RequestBody editEmpresaDTO editEmpresaDTO){
        anuncianteService.editarAnunciante(editEmpresaDTO.searchId(), editEmpresaDTO.novaEmpresa());
        return true;
    }

    @PostMapping("/marcarEntrada")
    public boolean marcarEntrada(@RequestBody MarcarPontoDTO marcarPontoDTO){
        anuncianteService.marcarEntrada(marcarPontoDTO);
        return true;
    }

    @GetMapping("/buscarJornada")
    public ResponseEntity<RetornarJornadaDeTrabalhoDTO> buscarJornadaDeTrabalho(@RequestBody BuscarJornadaDTO buscarJornadaDTO){
        return ResponseEntity.ok(anuncianteService.buscarJornadaDeTrabalho(buscarJornadaDTO));
    }

    @GetMapping("buscarNotificacoes")
    public ResponseEntity<RetornoNotificacaoDTO> buscarNotificacoes(@RequestBody searchDTO searchCNPJ){
        return ResponseEntity.ok(anuncianteService.buscarNotificacoes(searchCNPJ));
    }

    @DeleteMapping("/deletarNotificacao")
    public boolean deletarNotificacao (@RequestBody deletarNotifcacaoDTO deletarNotifcacaoDTO){
        anuncianteService.deletarNotificacao(deletarNotifcacaoDTO);
        return true;
    }
}
