package com.grupo6.ConectaJob.Model.Anunciante;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnuncianteRepository extends MongoRepository<Anunciante, String> {

    Anunciante findAnuncianteById(String id);

    Anunciante findAnuncianteByCpfAtrelado(String cpf);
}
