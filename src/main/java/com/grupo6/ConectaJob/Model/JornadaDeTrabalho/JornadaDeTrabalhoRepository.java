package com.grupo6.ConectaJob.Model.JornadaDeTrabalho;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JornadaDeTrabalhoRepository extends MongoRepository<JornadaDeTrabalho, String> {
    List<JornadaDeTrabalho> findByEmpresaAtreladaCNPJ(String CNPJ);
}
