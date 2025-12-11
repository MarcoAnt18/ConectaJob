package com.grupo6.ConectaJob.Controller.UsuarioController;

import com.grupo6.ConectaJob.Model.DTO.SearchCPF;
import com.grupo6.ConectaJob.Model.userGeneric.Usuario;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Usuario")

public class UserTrabalhadorController extends UsuarioController{

    @PostMapping("/CreateUsuario")
    public boolean criarusuario(@RequestBody Usuario usuario){
        usuarioservice.CreateUsuario(usuario);
        return true;
    }

    @PutMapping("/editUsuario")
    public boolean atualizarUsuario(@RequestBody SearchCPF searchcpfDTO, Usuario novouser){
        usuarioservice.editUsuario(searchcpfDTO, novouser);
        return true;
    }
}
