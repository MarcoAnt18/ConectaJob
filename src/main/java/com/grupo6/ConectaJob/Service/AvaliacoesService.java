package com.grupo6.ConectaJob.Service;

import com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson.notFound;
import com.grupo6.ConectaJob.Model.DTO.createavaliacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.returnTrabalhadorDTO;
import com.grupo6.ConectaJob.Model.DTO.returnavaliacao;
import com.grupo6.ConectaJob.Model.avaliacao;
import com.grupo6.ConectaJob.Model.DTO.novaAvaliacaoDTO;
import com.grupo6.ConectaJob.Model.vaga.useravaliacaoRepository;
import com.grupo6.ConectaJob.Model.vaga.userTrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AvaliacoesService {
    @Autowired
    private useravaliacaoRepository avaliacaoRepository;
    private userTrabalhadorRepository trabalhadorRepository;

    public boolean criaravaliacao (createavaliacaoDTO avaliacaoDTO, returnTrabalhadorDTO trabalhadorDTO){
        var userAvaliado = trabalhadorRepository.finduserTrabalhadorbyCPF(trabalhadorDTO.cpf());

        if (userAvaliado == null){
            throw new notFound("usuario nao encontrado");
        }

        List<avaliacao> avaliacoes = userAvaliado.getAvaliacoes();

        for (avaliacao a: avaliacoes){
            if (a.getCNPJAvaliador().equals(avaliacaoDTO.cnpj())){
                throw new notFound("a avaliacao ja existe");
            } else {
                var avaliacaonova = new avaliacao(avaliacaoDTO.Avaliador(), avaliacaoDTO.Avaliado(), avaliacaoDTO.cnpj(), avaliacaoDTO.nota(), avaliacaoDTO.comentario());
                avaliacoes.add(avaliacaonova);
            }
        }

        userAvaliado.setAvaliacoes(avaliacoes);
        trabalhadorRepository.save(userAvaliado);

        return true;
    }

    public boolean deleteavaliacao(returnTrabalhadorDTO trabalhadorDTO, String avaliadordel){
        var userdeletado = trabalhadorRepository.finduserTrabalhadorbyCPF(trabalhadorDTO.cpf());

        if (userdeletado == null){
            throw new notFound("usuario nao encontrado");
        }

        List<avaliacao> avaliacoes = userdeletado.getAvaliacoes();
        int verificador = 0;

        for (avaliacao a: avaliacoes){ //procurar pelo CNPJ a avaliacao a ser apagada, apagar e retornar o array novo ao usuario certo
            if (a.getCNPJAvaliador().equals(avaliadordel)){
                avaliacoes.remove(a);
                verificador = 1;
            }
        }

        if (verificador == 0){
            throw new notFound("a avaliacao nao foi encontrada");
        }

        userdeletado.setAvaliacoes(avaliacoes);
        trabalhadorRepository.save(userdeletado);
        return true;

    }

    public returnavaliacao buscaravaliacao(String CNPJ){
        var avaliacaorequerida = avaliacaoRepository.findavaliacaobyCNPJ(CNPJ);

        if (avaliacaorequerida == null){
            throw new notFound("a avaliacao nao existe");
        }

        System.out.println(avaliacaorequerida);
        return new returnavaliacao(avaliacaorequerida.getAvaliador(), avaliacaorequerida.getCNPJAvaliador(), avaliacaorequerida.getComentario(), avaliacaorequerida.getNota());

    }

    public boolean editavaliacao(String CPF, novaAvaliacaoDTO AvaliacaoDTO){
        var userAntigo = trabalhadorRepository.finduserTrabalhadorbyCPF(CPF);

        List<avaliacao> avaliacoes = userAntigo.getAvaliacoes();
        Boolean verificador = false;
        //primeiro procurar o usuario certo, depois pegar a avaliacao correta, salvar a avaliacao numa variavel var, criar uma nova avaliacao com o construtor, e verificar se um campo foi mudado e adicionar a nova avaliacao ao usuario e salva no repositorio.

        for (avaliacao a: avaliacoes){
            if(a.getCNPJAvaliador().equals(AvaliacaoDTO.CNPJ())){
                var novaAvaliacao = new avaliacao((AvaliacaoDTO.Avaliador() == null) ? a.getAvaliador() : AvaliacaoDTO.Avaliador(), (AvaliacaoDTO.CNPJ() == null) ? a.getCNPJAvaliador() : AvaliacaoDTO.CNPJ(), (AvaliacaoDTO.Avaliado() == null) ? a.getAvaliado() : AvaliacaoDTO.Avaliado(), (AvaliacaoDTO.nota() == null) ? a.getNota() : AvaliacaoDTO.nota(), (AvaliacaoDTO.Comentario() == null) ? a.getComentario() : AvaliacaoDTO.Comentario());
                a.setId(userAntigo.getId());
                avaliacoes.add(novaAvaliacao);
                userAntigo.setAvaliacoes(avaliacoes);
                trabalhadorRepository.save(userAntigo);
                verificador = true;
                break;
            }
        }
        if (verificador == false){
            throw new notFound("avaliacao nao encontrada");
        }

        return true;

    }


}
