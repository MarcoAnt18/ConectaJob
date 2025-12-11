package com.grupo6.ConectaJob.Controller.UsuarioController;

import com.grupo6.ConectaJob.Model.DTO.SearchCPF;
import com.grupo6.ConectaJob.Model.DTO.retornoUsuarioDTO;
import com.grupo6.ConectaJob.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UsuarioController {

    @Autowired
    protected UsuarioService usuarioservice;

    @GetMapping("/BuscarUsuario")
    public ResponseEntity<retornoUsuarioDTO> buscarUsuario(SearchCPF cpftrabalhador){
        return ResponseEntity.ok(usuarioservice.BuscarUsuario(cpftrabalhador));
    }

    @GetMapping("/DeleteUsuario")
    public boolean deletarUsuario(@RequestBody SearchCPF cpfTrabalhador){
        usuarioservice.DeleteUsuario(cpfTrabalhador);
        return true;
    }
}
