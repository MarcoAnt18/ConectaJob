package com.grupo6.ConectaJob.Controller.AnuncioController;

import com.grupo6.ConectaJob.Model.DTO.EditVagaDTO;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaga")
public class VagaController extends AnuncioController{

    @PostMapping("/criarVaga")
    public boolean criarVaga(@RequestBody VagaTrabalho vagaTrabalho){
        anuncioService.createAnuncio(vagaTrabalho);
        return true;
    }

    @PutMapping("/editarVaga")
    public boolean editarVaga(@RequestBody EditVagaDTO editVagaDTO){
        anuncioService.editarAnuncio(editVagaDTO.searchAnuncioDTO(), editVagaDTO.novaVaga());
        return true;
    }
}
