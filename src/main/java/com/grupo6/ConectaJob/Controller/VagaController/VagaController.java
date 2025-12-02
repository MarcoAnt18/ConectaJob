package com.grupo6.ConectaJob.Controller.VagaController;

import com.grupo6.ConectaJob.Model.DTO.Anuncio.RetornoAnuncioDTO;
import com.grupo6.ConectaJob.Model.DTO.EditVagaDTO;
import com.grupo6.ConectaJob.Model.DTO.SearchAnuncioDTO;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import com.grupo6.ConectaJob.Service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaga")
public class VagaController {
    @Autowired
    private AnuncioService anuncioService;

    @PostMapping("/criarVaga")
    public boolean criarVaga(@RequestBody VagaTrabalho vagaTrabalho){
        anuncioService.createAnuncio(vagaTrabalho);
        return true;
    }

    @GetMapping("/buscarVaga")
    public ResponseEntity<RetornoAnuncioDTO> ProcurarVagaIndividual(@RequestBody SearchAnuncioDTO searchAnuncio){
        return ResponseEntity.ok(anuncioService.BuscarAnuncio(searchAnuncio));
    }

    /*@GetMapping("/buscarTodasVagas")
    public ResponseEntity<List<Anuncio>> buscaTodasVagas(){
        return ResponseEntity.ok(anuncioService.buscaTodosAnuncios());
    }*/

    @PutMapping("/editarVaga")
    public boolean editarAnuncio(@RequestBody EditVagaDTO editVagaDTO){
        anuncioService.editarAnuncio(editVagaDTO.searchAnuncioDTO(), editVagaDTO.novaVaga());
        return true;
    }

    @DeleteMapping("/deletarVaga")
    public boolean deletarVaga(@RequestBody SearchAnuncioDTO searchAnuncio){
        anuncioService.deletarAnuncio(searchAnuncio.nomeAnuncio(), searchAnuncio.anuncianteResponsavelId());
        return true;
    }
}
