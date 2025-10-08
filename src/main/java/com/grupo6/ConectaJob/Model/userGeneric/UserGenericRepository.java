package com.grupo6.ConectaJob.Model.userGeneric;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserGenericRepository extends MongoRepository <userGeneric,String> {
    UserDetails findByCpf(String cpf);
}
