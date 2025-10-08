package com.grupo6.ConectaJob.ConfgSeguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class ImplementacoesSeguranca {
    @Bean
    public PasswordEncoder passwordEnconder (){
        return new BCryptPasswordEncoder();
    }

}
