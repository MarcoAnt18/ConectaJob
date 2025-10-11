package com.grupo6.ConectaJob.Model.vaga;

import com.grupo6.ConectaJob.Model.userTrabalhador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface userTrabalhadorRepository extends MongoRepository {
    @Query("{ '_Avaliador': ?0 }")
    userTrabalhador finduserTrabalhadorbyCPF(String CPF);
}
