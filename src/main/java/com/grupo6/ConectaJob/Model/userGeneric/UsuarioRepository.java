package com.grupo6.ConectaJob.Model.userGeneric;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository <Usuario, String> {
    Usuario findusuariobycpf(String cpf);
}
