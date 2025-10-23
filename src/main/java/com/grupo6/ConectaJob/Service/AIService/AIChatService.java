package com.grupo6.ConectaJob.Service.AIService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import com.grupo6.ConectaJob.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AIChatService{
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmpresaService empresaService;

    private final AIChatServiceInterface chatService;

    public AIChatService(AIChatServiceInterface aiChatServiceInterface){
        this.chatService = aiChatServiceInterface;
    }

    public String getRespostaIA(searchVaga searchVaga) throws Exception{

        vagaTrabalho vagaProcurada = empresaService.buscarVagaTrabalho(searchVaga.nomeVaga(), searchVaga.empresaResponsavelCNPJ());

        if(vagaProcurada == null){
            throw new notFound("Vaga não encontrada");
        }

        String vagaParaAnalisar = converterClasseParaJson(vagaProcurada);

        System.out.println("Vaga convertida: " + vagaParaAnalisar);

        return chatService.getResposta(vagaParaAnalisar);
    }

    public String converterClasseParaJson(vagaTrabalho vagaParaConverter) throws Exception{
        return objectMapper.writeValueAsString(vagaParaConverter);
    }

}
