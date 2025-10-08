package com.grupo6.ConectaJob.Controller.Delete;

import com.grupo6.ConectaJob.Model.DTO.Notificacao.deletarNotifcacaoDTO;
import com.grupo6.ConectaJob.Model.DTO.searchDTO;
import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Delete")
public class DeleteEmpresas {

    @Autowired
    private EmpresaService empresaService;

    @DeleteMapping("/Empresa")
    public boolean deletarEmpresa (@RequestBody searchDTO searchCNPJ){
        empresaService.deletarEmpresa(searchCNPJ);
        return true;
    }

    @DeleteMapping("/Vaga")
    public boolean deletarVaga (@RequestBody searchVaga searchVaga){
        empresaService.deleteVaga(searchVaga.nomeVaga(), searchVaga.empresaResponsavelCNPJ());
        return true;
    }

    //Para depois
    @DeleteMapping("/notificacoes")
    public boolean deletarNotificacao (@RequestBody deletarNotifcacaoDTO deletarNotifcacaoDTO){
        empresaService.apagarNotificacao(deletarNotifcacaoDTO.nomeUsuario(),
                                         deletarNotifcacaoDTO.nomeVaga(),
                                         deletarNotifcacaoDTO.empresaCNPJ());
        return true;
    }

}