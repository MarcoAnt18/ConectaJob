package com.grupo6.ConectaJob.Controller.AIController;

import com.grupo6.ConectaJob.Service.AIService.AIChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {
    private final AIChatService chatService;

    public AIController(AIChatService aiChatService){
        this.chatService = aiChatService;
    }

    @GetMapping("/VerificarContrato")
    public String verificarContrato(@RequestBody String pergunta){
        return chatService.getResposta(pergunta);
    }
}
