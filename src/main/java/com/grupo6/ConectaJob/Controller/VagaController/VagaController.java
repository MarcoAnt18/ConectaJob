package com.grupo6.ConectaJob.Controller.VagaController;

import com.grupo6.ConectaJob.Model.DTO.criarVagaDTO;
import com.grupo6.ConectaJob.Model.DTO.editVagaDTO;
import com.grupo6.ConectaJob.Model.DTO.retornoVagaExistente;
import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import com.grupo6.ConectaJob.Service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaga")
public class VagaController {
    @Autowired
    private VagaService vagaService;

    @PostMapping("/criarVaga")
    public boolean criarVaga(@RequestBody criarVagaDTO criarVagaDTO){
        vagaService.createVaga(criarVagaDTO);
        return true;
    }

    @GetMapping("/buscarVaga")
    public ResponseEntity<retornoVagaExistente> ProcurarVagaIndividual(@RequestBody searchVaga searchVaga){
        return ResponseEntity.ok(vagaService.BuscarVagaPorNome(searchVaga));
    }

    @GetMapping("/buscarTodasVagas")
    public ResponseEntity<List<vagaTrabalho>> buscaTodasVagas(){
        return ResponseEntity.ok(vagaService.buscaTodasVagas());
    }

    @PutMapping("/editarVaga")
    public boolean editarVaga(@RequestBody editVagaDTO editVagaDTO){
        vagaService.editarVaga(editVagaDTO.searchVaga(), editVagaDTO.novaVagaDTO());
        return true;
    }

    @DeleteMapping("/deletarVaga")
    public boolean deletarVaga(@RequestBody searchVaga searchVaga){
        vagaService.deletarVaga(searchVaga.nomeVaga(), searchVaga.empresaResponsavelCNPJ());
        return true;
    }
}
