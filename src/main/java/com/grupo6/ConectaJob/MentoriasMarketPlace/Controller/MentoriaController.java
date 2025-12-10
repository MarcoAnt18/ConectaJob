package com.grupo6.ConectaJob.MentoriasMarketPlace.Controller;

import com.grupo6.ConectaJob.Controller.AnuncioController.AnuncioController;
import com.grupo6.ConectaJob.MentoriasMarketPlace.Model.DTO.Mentoria.EditMentoriaDTO;
import com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentoria.Mentoria;
import com.grupo6.ConectaJob.Model.DTO.EditVagaDTO;
import com.grupo6.ConectaJob.Model.vaga.VagaTrabalho;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentoria")
public class MentoriaController extends AnuncioController{

    @PostMapping("/criarMentoria")
    public boolean criarMentoria(@RequestBody Mentoria mentoria){
        anuncioService.createAnuncio(mentoria);
        return true;
    }

    @PutMapping("/editarMentoria")
    public boolean editarMentoria(@RequestBody EditMentoriaDTO editMentoriaDTO){
        anuncioService.editarAnuncio(editMentoriaDTO.searchAnuncioDTO(), editMentoriaDTO.novaMentoria());
        return true;
    }
}
