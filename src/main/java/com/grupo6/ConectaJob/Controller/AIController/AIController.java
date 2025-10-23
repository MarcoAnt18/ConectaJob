package com.grupo6.ConectaJob.Controller.AIController;

import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Service.AIService.AIChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    @Autowired
    private AIChatService aiChatService;

    @GetMapping("/VerificarContrato")
    public String verificarContrato(@RequestBody searchVaga searchVaga) throws Exception{
        return aiChatService.getRespostaIA(searchVaga) ;
    }
}
