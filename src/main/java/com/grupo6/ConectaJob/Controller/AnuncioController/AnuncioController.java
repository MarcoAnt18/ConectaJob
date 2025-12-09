package com.grupo6.ConectaJob.Controller.AnuncioController;

import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.DTO.SearchAnuncioDTO;
import com.grupo6.ConectaJob.Service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AnuncioController {
    @Autowired
    protected AnuncioService anuncioService;

    @GetMapping("/buscarAnuncio")
    public ResponseEntity<RetornoAnuncioDTO> ProcurarVagaIndividual(@RequestBody SearchAnuncioDTO searchAnuncio){
        return ResponseEntity.ok(anuncioService.BuscarAnuncio(searchAnuncio));
    }

    @DeleteMapping("/deletarAnuncio")
    public boolean deletarVaga(@RequestBody SearchAnuncioDTO searchAnuncio){
        anuncioService.deletarAnuncio(searchAnuncio.nomeAnuncio(), searchAnuncio.anuncianteResponsavelId());
        return true;
    }
}
