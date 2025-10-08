package com.grupo6.ConectaJob.Controller.NovosRegistros;

import com.grupo6.ConectaJob.Model.DTO.createEmpresaDTO;
import com.grupo6.ConectaJob.Model.DTO.criarVagaDTO;
import com.grupo6.ConectaJob.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/novosRegistros")
public class novosRegistrosController {
    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/criarEmpresa")
    public boolean criarEmpresa (@RequestBody createEmpresaDTO createEmpresaDTO){
        empresaService.createEmpresa(createEmpresaDTO);
        return true;
    }

    @PostMapping("/criarVaga")
    public boolean criarVaga (@RequestBody criarVagaDTO criarVagaDTO){
        empresaService.createVaga(criarVagaDTO);
        return true;
    }



}
