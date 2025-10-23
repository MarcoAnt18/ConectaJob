package com.grupo6.ConectaJob.Controller.Update;

import com.grupo6.ConectaJob.Model.DTO.*;
import com.grupo6.ConectaJob.Service.VagaService;
import com.grupo6.ConectaJob.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Update")
public class UpdateEmpresa {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private VagaService vagaService;

    @PutMapping("/Empresa")
    public boolean editarEmpresa(@RequestBody editEmpresaDTO editEmpresaDTO){
        empresaService.editarEmpresa(editEmpresaDTO.searchCNPJ(), editEmpresaDTO.novaEmpresa());
        return true;
    }

    @PutMapping("/Vaga")
    public boolean editarVaga(@RequestBody editVagaDTO editVagaDTO){
        vagaService.editVaga(editVagaDTO.searchVaga(), editVagaDTO.novaVagaDTO());
        return true;
    }
}