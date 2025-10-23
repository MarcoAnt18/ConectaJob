package com.grupo6.ConectaJob.Service.AIService;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OpenAIChatServiceInterface implements AIChatServiceInterface {
    private final ChatClient chatClient;

    public OpenAIChatServiceInterface(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    String templateVagaParaAnalisar = "Gostaria que você analisasse uma vaga de trabalho"  +
            "e verificasse se esta de acordo com as leis trabalhistas. Diva em tópicos os problemas encontrados. " +
            "Em caso de alguma irregularidade, cite o está faltando ou como deveria ser " +
            "Envie apenas os tópicos na resposta. Queria que ignorasse empresaReponsavelCNPJ e  numeroDeVagasAbertas" +
            "Essa é a vaga: ";


   public String conferirVaga(String VagaParaAnalisar){
       return chatClient.prompt().user(templateVagaParaAnalisar + VagaParaAnalisar).call().content();
   }

}
