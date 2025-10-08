package com.grupo6.ConectaJob.ConfgSeguranca;

import com.grupo6.ConectaJob.Model.userGeneric.UserGenericRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class MeuFiltroSeguranca extends OncePerRequestFilter {
    @Autowired
    private tokenConfig tokenConfig;
    @Autowired
    private UserGenericRepository UserGenericRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var tokenRecuperado = recuperandoToken(request);
        if (tokenRecuperado != null){
            var tokenValidado = tokenConfig.verificaValidadeToken(tokenRecuperado);
            var userTrabalhador = UserGenericRepository.findByCpf(tokenValidado);
            var authentication = new UsernamePasswordAuthenticationToken(userTrabalhador,null,userTrabalhador.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }

    private String recuperandoToken (HttpServletRequest JSON){
        var Token = JSON.getHeader("Authorization");
        if (Token != null){
            Token = Token.replace("Bearer","").trim();
            return Token;
        }
        return  null;
    }
}
