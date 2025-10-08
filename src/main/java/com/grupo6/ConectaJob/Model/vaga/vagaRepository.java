package com.grupo6.ConectaJob.Model.vaga;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface vagaRepository extends MongoRepository <vagaTrabalho,String> {
}
