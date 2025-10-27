package com.grupo6.ConectaJob.Service.AIService;

import com.grupo6.ConectaJob.Model.DTO.ConferirVaga.retornoConferirVaga;

public interface AIChatServiceInterface {
    retornoConferirVaga conferirVaga(String contratoParaAnalisar, String vagaParaAnalisar);
}
