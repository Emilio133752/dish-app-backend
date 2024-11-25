// Salve em: src/main/java/com/ibeus/Comanda/Digital/controller/HomeController.java
package com.ibeus.Comanda.Digital.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bem-vindo à API da Comanda Digital";
    }
    
    @GetMapping("/api/status")
    public String status() {
        return "API está funcionando normalmente";
    }
}