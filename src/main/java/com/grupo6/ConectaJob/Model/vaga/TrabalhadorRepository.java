package com.grupo6.ConectaJob.Model.vaga;

import com.grupo6.ConectaJob.Model.userTrabalhador;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrabalhadorRepository extends MongoRepository <userTrabalhador, String>{

    userTrabalhador findbyCEP(String CEP);
}
