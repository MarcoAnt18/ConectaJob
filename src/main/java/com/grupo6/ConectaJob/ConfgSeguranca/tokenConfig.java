package com.grupo6.ConectaJob.ConfgSeguranca;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.grupo6.ConectaJob.Model.userGeneric.userGeneric;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Service
public class tokenConfig {
    public String TokenGenerate (userGeneric usuario){
        try {
            var algoritmo = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("API-ConectaJob")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);}
    }



    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

    public static String verificaValidadeToken (String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256("secret");
            return JWT.require(algoritmo)
                    .withIssuer("API-ConectaJob")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token inválido ou expirado");
        }
    }



}
