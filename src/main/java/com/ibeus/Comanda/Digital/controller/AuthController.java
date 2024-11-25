package com.ibeus.Comanda.Digital.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ibeus.Comanda.Digital.model.User;
import com.ibeus.Comanda.Digital.service.JwtProvider;
import com.ibeus.Comanda.Digital.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtProvider tokenProvider;
    private final UserService userService;

    public AuthController(JwtProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = this.userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        User users = user.get();

        if (users.getEmail().equals(loginRequest.getEmail()) && users.getPassword().equals(loginRequest.getPassword())) {
            String token = tokenProvider.generateToken(loginRequest.getEmail(), users.getRole(), users.getName(), users.getPhoneNumber());
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }

    static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return this.email;
        }

        public String getPassword() {
            return this.password;
        }
    }

    static class LoginResponse {
        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
