package com.grupo6.ConectaJob.Service.AIService;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAIChatService implements AIChatService{
    private final ChatClient chatClient;

    public OpenAIChatService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

   public String getResposta(String pergunta){
       return chatClient.prompt().user(pergunta).call().content();
   }

}
