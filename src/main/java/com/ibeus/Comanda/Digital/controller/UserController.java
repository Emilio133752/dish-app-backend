package com.ibeus.Comanda.Digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ibeus.Comanda.Digital.model.User;
import com.ibeus.Comanda.Digital.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        User users = this.userService.findByEmail(user.getEmail());
        if(users != null) {
            return ResponseEntity.status(400).body("Esse email já existe!");
        }
        User res = userService.create(user); 
        return res != null
            ? ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!") 
            : ResponseEntity.status(400).body("Erro ao criar usuário!");
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}