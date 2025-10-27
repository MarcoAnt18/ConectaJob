package com.grupo6.ConectaJob.Service.AIService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.ConferirVaga.retornoConferirVaga;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import com.grupo6.ConectaJob.Service.VagaService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    public retornoConferirVaga verificarContrato(MultipartFile contrato, String nomeVaga, String empresaResponsavelCNPJ) throws Exception{
        vagaTrabalho vagaProcurada = vagaService.buscarVagaTrabalho(nomeVaga, empresaResponsavelCNPJ);

        if(vagaProcurada == null){
            throw new notFound("Vaga não encontrada");
        }

        String vagaParaAnalisar = converterClasseParaJson(vagaProcurada);

        String contratoParaAnalisar = converterContratoParaString(contrato);

        return chatService.conferirVaga(contratoParaAnalisar ,vagaParaAnalisar);
    }

    public String converterClasseParaJson(vagaTrabalho vagaParaConverter) throws Exception{
        return objectMapper.writeValueAsString(vagaParaConverter);
    }

    public String converterContratoParaString(MultipartFile contrato){
        if(!contrato.getContentType().equalsIgnoreCase("application/pdf")){
            throw new notFound("Arquivo enviado não é um PDF");
        }

        try(PDDocument arquivo = PDDocument.load(contrato.getInputStream())){

            PDFTextStripper extrator = new PDFTextStripper();
            String contratoString = extrator.getText(arquivo);

            return contratoString.trim().isEmpty() ? "PDF sem texto" : contratoString;

        }catch (IOException e){
            throw new notFound("Erro ao converter o PDF");
        }
    }
}
