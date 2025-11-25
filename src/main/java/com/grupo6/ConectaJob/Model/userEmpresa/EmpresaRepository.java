package com.grupo6.ConectaJob.Model.userEmpresa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EmpresaRepository extends MongoRepository <Empresa,String> {

    @Query("{ '_id': ?0 }")
    Empresa findEmpresaByID(String id);

    @Query("{'CNPJ': ?0}")
    Empresa findEmpresaByCNPJ(String CNPJ);
}
