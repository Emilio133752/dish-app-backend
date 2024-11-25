package com.ibeus.Comanda.Digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibeus.Comanda.Digital.model.Categoria;
import com.ibeus.Comanda.Digital.model.User;
import com.ibeus.Comanda.Digital.service.CategoriaService;
import com.ibeus.Comanda.Digital.service.UserService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
    @Autowired
    private CategoriaService categoryService;

    @GetMapping
    public List<Categoria> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Categoria getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public Categoria createCategory(@RequestBody Categoria user) {
        return categoryService.create(user);
    }

    @PutMapping("/{id}")
    public Categoria updateCategory(@PathVariable Long id, @RequestBody Categoria user) {
        return categoryService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.ok("Categoria excluída com sucesso!");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
