package com.grupo6.ConectaJob.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class testController {
    @GetMapping
    public String HelloWord (){
        return "Hello Word";
    }

    @GetMapping("/token")
    public String testeToken (){
        return "Hello token";
    }

}
