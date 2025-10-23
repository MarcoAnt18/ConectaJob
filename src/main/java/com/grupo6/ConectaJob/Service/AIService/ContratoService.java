package com.grupo6.ConectaJob.Service.AIService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import com.grupo6.ConectaJob.Service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VagaService vagaService;

    private final AIChatServiceInterface chatService;

    public ContratoService(AIChatServiceInterface aiChatServiceInterface){
        this.chatService = aiChatServiceInterface;
    }

    public String getRespostaIA(searchVaga searchVaga) throws Exception{

        vagaTrabalho vagaProcurada = vagaService.buscarVagaTrabalho(searchVaga.nomeVaga(), searchVaga.empresaResponsavelCNPJ());

        if(vagaProcurada == null){
            throw new notFound("Vaga não encontrada");
        }

        String vagaParaAnalisar = converterClasseParaJson(vagaProcurada);

        return chatService.conferirVaga(vagaParaAnalisar);
    }

    public String converterClasseParaJson(vagaTrabalho vagaParaConverter) throws Exception{
        return objectMapper.writeValueAsString(vagaParaConverter);
    }

}
