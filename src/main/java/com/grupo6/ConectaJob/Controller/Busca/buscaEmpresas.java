package com.grupo6.ConectaJob.Controller.Busca;

import com.grupo6.ConectaJob.Model.DTO.retornoEmpresaExiste;
import com.grupo6.ConectaJob.Model.DTO.retornoVagaExistente;
import com.grupo6.ConectaJob.Model.DTO.searchDTO;
import com.grupo6.ConectaJob.Model.DTO.searchVaga;
import com.grupo6.ConectaJob.Model.notificacao.Notificacao;
import com.grupo6.ConectaJob.Model.vaga.vagaTrabalho;
import com.grupo6.ConectaJob.Service.VagaService;
import com.grupo6.ConectaJob.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Search")
public class buscaEmpresas {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private VagaService vagaService;

    @GetMapping("/Empresas")
    public ResponseEntity<retornoEmpresaExiste> ProcurarEmpresasExistentes (@RequestBody searchDTO searchCNPJ){
        return ResponseEntity.ok(empresaService.buscaEmpresa(searchCNPJ.cnpj()));
    }

    @GetMapping("/Vaga")
    public ResponseEntity<retornoVagaExistente> ProcurarVagaIndividual(@RequestBody searchVaga searchVaga){
        return ResponseEntity.ok(vagaService.BuscarVagaPorNome(searchVaga));
    }

    @GetMapping("/VagasTodas")
    public ResponseEntity<List<vagaTrabalho>> ProcurarVagasExistentes (){
        return ResponseEntity.ok(vagaService.buscaTodasVagas());
    }

    @GetMapping("notificacoes")
    public ResponseEntity<List<Notificacao>> ListarNotificacoes(@RequestBody searchDTO searchCNPJ){
        return ResponseEntity.ok(empresaService.buscarNOtificacoes(searchCNPJ));
    }
}
