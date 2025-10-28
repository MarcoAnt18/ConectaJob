package com.grupo6.ConectaJob.Controller.EmpresaController;

import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.MarcarPontoDTO;
import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.RetornarJornadaDeTrabalhoDTO;
import com.grupo6.ConectaJob.Model.DTO.Notificacao.BuscarJornadaDTO;
import com.grupo6.ConectaJob.Model.DTO.createEmpresaDTO;
import com.grupo6.ConectaJob.Model.DTO.editEmpresaDTO;
import com.grupo6.ConectaJob.Model.DTO.retornoEmpresaExiste;
import com.grupo6.ConectaJob.Model.DTO.searchDTO;
import com.grupo6.ConectaJob.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping("/criarEmpresa")
    public boolean criarEmpresa (@RequestBody createEmpresaDTO createEmpresaDTO){
        empresaService.createEmpresa(createEmpresaDTO);
        return true;
    }

    @GetMapping("/buscarEmpresa")
    public ResponseEntity<retornoEmpresaExiste> ProcurarEmpresasExistentes (@RequestBody searchDTO searchCNPJ){
        return ResponseEntity.ok(empresaService.buscaEmpresa(searchCNPJ.cnpj()));
    }

    @PutMapping("/editarEmpresa")
    public boolean editarEmpresa(@RequestBody editEmpresaDTO editEmpresaDTO){
        empresaService.editarEmpresa(editEmpresaDTO.searchCNPJ(), editEmpresaDTO.novaEmpresa());
        return true;
    }

    @DeleteMapping("/deletarEmpresa")
    public boolean deletarEmpresa (@RequestBody searchDTO searchCNPJ){
        empresaService.deletarEmpresa(searchCNPJ);
        return true;
    }

    @PostMapping("/marcarEntrada")
    public boolean marcarEntrada(@RequestBody MarcarPontoDTO marcarPontoDTO){
        empresaService.marcarEntrada(marcarPontoDTO);
        return true;
    }

    @GetMapping("/buscarJornada")
    public ResponseEntity<RetornarJornadaDeTrabalhoDTO> buscarJornadaDeTrabalho(@RequestBody BuscarJornadaDTO buscarJornadaDTO){
        return ResponseEntity.ok(empresaService.buscarJornadaDeTrabalho(buscarJornadaDTO));
    }
}
