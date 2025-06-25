package com.Bitech.rent_a_car.Controller.Padrao;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("")
public class rotaoPadraoController {
    
    @GetMapping("/")
    public String get() {
    System.out.println("API Conectada!");
        return new String("Bem vindo/(a) a página inicial do Me Aluga Só!");
    }
}
