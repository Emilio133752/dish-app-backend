package com.ibeus.Comanda.Digital.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
    private static String secret = "@$?&&#212HOlDS+U*hsias=!-102-0e0hP0";
    private static final Key secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    private static final long EXPIRATION_TIME = 3600000; // 1 hora

    // Gera o token
    public String generateToken(String email, String role, String name, String phone) {
        return Jwts.builder()
            .claim("email", email) // Subject
            .claim("role", role)
            .claim("name", name)
            .claim("phone", phone)
            .claim("iat", new Date()) // Issued At
            .claim("exp", new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiration
            .signWith(secretKey)
            .compact();
    }

    // Valida o token e extrai as informações do usuário
    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(secretKey) // Set the key once
                .build()
                .parseClaimsJws(token) // Parse the JWT
                .getBody();

            return claims.getSubject(); // Retorna o usuário do token
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Token não suportado", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token malformado", e);
        } catch (SignatureException e) {
            throw new RuntimeException("Assinatura do token inválida", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar token", e);
        }
    }
}
