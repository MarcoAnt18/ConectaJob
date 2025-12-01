package com.grupo6.ConectaJob.Model.Anuncio;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnuncioRepository extends MongoRepository<Anuncio, String> {
    Anuncio findAnuncioByAnuncioId(String id);
}
