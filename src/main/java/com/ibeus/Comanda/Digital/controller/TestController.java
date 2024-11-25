package com.ibeus.Comanda.Digital.controller;

import com.ibeus.Comanda.Digital.model.Produto;
import com.ibeus.Comanda.Digital.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String test() {
        return "API funcionando corretamente!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Olá! Endpoint de teste.";
    }

    @GetMapping("/products")
    public ResponseEntity<String> testConnection() {
        try {
            // Tenta listar os produtos do banco de dados
            List<Produto> produtos = produtoService.listarTodos();
            return ResponseEntity.ok("Conexão com o banco de dados estabelecida com sucesso! Total de produtos: " + produtos.size());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro na conexão com o banco de dados: " + e.getMessage());
        }
    }
}
