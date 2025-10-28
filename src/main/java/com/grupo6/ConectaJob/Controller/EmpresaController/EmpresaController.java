package com.grupo6.ConectaJob.Controller.EmpresaController;

import com.grupo6.ConectaJob.Model.DTO.JornadaDeTrabalho.MarcarPontoDTO;
import com.grupo6.ConectaJob.Service.JornadaDeTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    JornadaDeTrabalhoService jornadaDeTrabalhoService;

    @PostMapping("/marcarEntrada")
    public boolean marcarEntrada(@RequestBody MarcarPontoDTO marcarPontoDTO){
        jornadaDeTrabalhoService.marcarEntrada(
                marcarPontoDTO.trabalhadorCPF(),
                marcarPontoDTO.empresaResponsavelCPNJ(),
                marcarPontoDTO.nomeVaga()
        );
        return true;
    }
}
