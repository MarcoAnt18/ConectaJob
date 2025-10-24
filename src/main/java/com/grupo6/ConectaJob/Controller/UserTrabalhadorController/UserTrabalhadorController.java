package com.grupo6.ConectaJob.Controller.UserTrabalhadorController;


import com.grupo6.ConectaJob.Service.EmpresaService;
import com.grupo6.ConectaJob.Service.UserTrabalhadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/Trabalhador")
public class UserTrabalhadorController {

    @Autowired
    private UserTrabalhadorService userTrabalhadorService;

    @GetMapping("/VerificarContrato")
    public String verificarContrato(
            @RequestParam("file") MultipartFile contrato,
            @RequestParam("nomeVaga") String nomeVaga,
            @RequestParam("empresaResponsavelCNPJ") String empresaResponsavelCNPJ
    ) throws Exception{
        return userTrabalhadorService.verificarContrato(contrato, nomeVaga, empresaResponsavelCNPJ);
    }

}
