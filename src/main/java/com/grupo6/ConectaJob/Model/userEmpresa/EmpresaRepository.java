package com.grupo6.ConectaJob.Model.userEmpresa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EmpresaRepository extends MongoRepository <empresa,String> {

    @Query("{ '_id': ?0 }")
    empresa findEmpresaByID(String id);

    @Query("{'CNPJ': ?0}")
    empresa findEmpresaByCNPJ(String CNPJ);
}
