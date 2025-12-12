package com.grupo6.ConectaJob.MentoriasMarketPlace.Controller;

import com.grupo6.ConectaJob.Controller.AnuncianteController.AnuncianteController;
import com.grupo6.ConectaJob.MentoriasMarketPlace.Model.DTO.Mentor.EditMentorDTO;
import com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentor.Mentor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentor")
public class MentorController extends AnuncianteController {

    @PostMapping("/criarMentor")
    public boolean criarMentor (@RequestBody Mentor mentor){
        anuncianteService.createAnunciante(mentor);
        return true;
    }

    @PutMapping("/editarMentor")
    public boolean editarMentor(@RequestBody EditMentorDTO editMentorDTO){
        anuncianteService.editarAnunciante(editMentorDTO.searchId(), editMentorDTO.novoMentor());
        return true;
    }
}
