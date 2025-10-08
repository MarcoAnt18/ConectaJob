package com.grupo6.ConectaJob.ConfgSeguranca;

import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginRecuperaInfoUser implements UserDetailsService {
    @Autowired
    private UserGenericRepository UserGenericRepository;
    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return UserGenericRepository.findByCpf(cpf);
    }
}
