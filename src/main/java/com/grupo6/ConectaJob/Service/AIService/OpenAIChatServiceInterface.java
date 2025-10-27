package com.grupo6.ConectaJob.Service.AIService;

import com.grupo6.ConectaJob.Model.DTO.ConferirVaga.retornoConferirVaga;
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

    String templateVagaParaAnalisar =
            "Este contrato: {Contrato} foi feito com base" +
            "nesta vaga de trabalho: {Vaga}. Primeiramente gostaria que verificasse se de fato as informações batem e estão de acordo," +
            "para que não haja incongruências entre as informações dos dois. Gere uma reposta JSON com um campo" +
            "\"diferencasContratoEVaga\" com uma lista de tópicos com todas as divergências encontradas." +
            "Posteriormente, queria que analisasse se a vaga de trabalho anterior esta de acordo com as leis trabalhistas" +
            "Coloque a reposta JSON no campo \"irregularidadesVaga\" com uma lista de tópicos com todas as " +
            "irregularidades encontradas. Em caso de irregularidades cite o que esta faltando ou como deveria ser";

   public retornoConferirVaga conferirVaga(String contrato, String VagaParaAnalisar){
        return chatClient.prompt()
                .user(UserSpec -> UserSpec
                        .text(templateVagaParaAnalisar)
                        .param("Contrato", contrato)
                        .param("Vaga", VagaParaAnalisar))
                .call()
                .entity(retornoConferirVaga.class);
   }
}
