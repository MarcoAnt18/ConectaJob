package com.grupo6.ConectaJob.Controller.NotificacaoController;

import com.grupo6.ConectaJob.Model.DTO.Notificacao.deletarNotifcacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.searchDTO;
import com.grupo6.ConectaJob.Model.notificacao.Notificacao;
import com.grupo6.ConectaJob.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {
    @Autowired
    private EmpresaService empresaService;

    //Faltou o create

    @GetMapping("buscarNotificacoes")
    public ResponseEntity<List<Notificacao>> ListarNotificacoes(@RequestBody searchDTO searchCNPJ){
        return ResponseEntity.ok(empresaService.buscarNOtificacoes(searchCNPJ));
    }

    //Para depois
    @DeleteMapping("/deletarNotificacoes")
    public boolean deletarNotificacao (@RequestBody deletarNotifcacaoDTO deletarNotifcacaoDTO){
        empresaService.apagarNotificacao(deletarNotifcacaoDTO.nomeUsuario(),
                deletarNotifcacaoDTO.nomeVaga(),
                deletarNotifcacaoDTO.empresaCNPJ());
        return true;
    }



}
