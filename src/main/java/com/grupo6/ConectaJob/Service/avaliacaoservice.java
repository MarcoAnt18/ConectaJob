package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.createavaliacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.returnTrabalhadorDTO;
import com.grupo6.ConectaJob.Model.DTO.returnavaliacao;
import com.grupo6.ConectaJob.Model.avaliacao;
import com.grupo6.ConectaJob.Model.DTO.novaAvaliacaoDTO;
import com.grupo6.ConectaJob.Model.vaga.useravaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AvaliacoesService {
    @Autowired
    private useravaliacaoRepository trabalhadorRepository;

    public boolean criaravaliacao (createavaliacaoDTO avaliacaoDTO, returnTrabalhadorDTO trabalhadorDTO){
        var checkavaliacao = trabalhadorRepository.findavaliacaobyAvaliador(avaliacaoDTO.Avaliador());

        for (avaliacao a: trabalhadorDTO.avaliacoes()){
            if(checkavaliacao == null){
                throw new notFound("avaliacao nao existe");
            }
        }

        var novaavaliacao = new avaliacao(avaliacaoDTO.cnpj(),avaliacaoDTO.Avaliador(),avaliacaoDTO.comentario(),avaliacaoDTO.nota(),avaliacaoDTO.cpf());
        trabalhadorRepository.save(novaavaliacao);
        return true;
    }

    public boolean deleteavaliacao(returnTrabalhadorDTO trabalhadorDTO, String avaliadordel){
        var procurador = trabalhadorRepository.findavaliacaobyAvaliador(avaliadordel);
        for(avaliacao a: trabalhadorDTO.avaliacoes()){
            if (procurador == null){
                throw new notFound("avaliacao nao existe");
            }
        }
        trabalhadorRepository.delete(procurador);
        return true;

    }

    public returnavaliacao buscaravaliacao(String avaliador){
        var avaliacaorequerida = trabalhadorRepository.findavaliacaobyAvaliador(avaliador);

        if (avaliacaorequerida == null){
            throw new notFound("a avaliacao nao existe");
        }

        System.out.println(avaliacaorequerida);
        return new returnavaliacao(avaliacaorequerida.getAvaliador(), avaliacaorequerida.getCNPJAvaliador(), avaliacaorequerida.getComentario(), avaliacaorequerida.getNota());

    }

    public boolean editavaliacao(String avaliador, novaAvaliacaoDTO AvaliacaoDTO){
        var avAntiga = trabalhadorRepository.findavaliacaobyAvaliador(avaliador);

        if (avAntiga == null){
            throw new notFound("avaliacao nao encontrada");
        }

        var novaAvaliacao = new avaliacao(AvaliacaoDTO.Avaliacao(), AvaliacaoDTO.CNPJ(), AvaliacaoDTO.nota(), AvaliacaoDTO.Comentario());
        novaAvaliacao.setId(avAntiga.getId());
        trabalhadorRepository.save(novaAvaliacao);
        return true;

    }


}
