package com.grupo6.ConectaJob.Controller.AnuncianteController;

import com.grupo6.ConectaJob.Model.DTO.Anunciante.RetornoAnuncianteDTO;
import com.grupo6.ConectaJob.Model.DTO.searchDTO;
import com.grupo6.ConectaJob.Service.AnuncianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class AnuncianteController {
    @Autowired
    protected AnuncianteService anuncianteService;

    @GetMapping("/buscarAnunciante")
    public ResponseEntity<RetornoAnuncianteDTO> ProcurarEmpresasExistentes (@RequestBody searchDTO searchId){
        return ResponseEntity.ok(anuncianteService.buscaAnunciante(searchId.id()));
    }

    @DeleteMapping("/deletarAnunciante")
    public boolean deletarEmpresa (@RequestBody searchDTO searchId){
        anuncianteService.deletarAnunciante(searchId);
        return true;
    }
}
