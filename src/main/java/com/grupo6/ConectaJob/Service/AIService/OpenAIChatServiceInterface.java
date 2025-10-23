package com.grupo6.ConectaJob.Service.AIService;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAIChatServiceInterface implements AIChatServiceInterface {
    private final ChatClient chatClient;

    public OpenAIChatServiceInterface(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    String templatePergutna = "Gostaria que você analisasse uma vaga de trabalho"  +
            "e verificasse se esta de acordo com as leis trabalhistas. Preciso que de respostas curtas e diretas" +
            "dividindo em tópicos os problemas encontrados. Essa é a vaga: ";

   public String getResposta(String VagaParaAnalisar){
       return chatClient.prompt().user(templatePergutna + VagaParaAnalisar).call().content();
   }

}
