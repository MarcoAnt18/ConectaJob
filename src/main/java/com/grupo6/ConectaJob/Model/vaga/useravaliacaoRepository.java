package com.grupo6.ConectaJob.Model.vaga;

import com.grupo6.ConectaJob.Model.avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface useravaliacaoRepository extends MongoRepository <avaliacao, String>{

    @Query("{ '_Avaliador': ?0 }")
    avaliacao findavaliacaobyAvaliador(String avaliador);
}
