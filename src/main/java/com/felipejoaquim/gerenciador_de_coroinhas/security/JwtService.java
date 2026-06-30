package com.felipejoaquim.gerenciador_de_coroinhas.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
    private final String secret = "asdhbahsbdhias-asdjbajdbaosdjba-khqeojb12398&@*31yna";
    private final String ISSUER = "gerenciador-de-coroinhas";

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(this.secret);
    }

    public String generateToken(Usuario usuario) {

        try {
            return JWT.create()
                        .withIssuer(ISSUER)
                        .withSubject(usuario.getEmail())
                        .withExpiresAt(getExpiration())
                        .sign(getAlgorithm());

        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar Token: ", e);
        }
    }

    public String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }

        if (!authHeader.startsWith("Bearer")) {
            return null;
        }

        return authHeader.split(" ")[1];
    }

    public String getEmail(String token){
        try {
            return JWT.require(getAlgorithm())
                        .withIssuer(ISSUER)
                        .build()
                        .verify(token).getSubject();

        } catch (RuntimeException e) {
            return "";
        }
    }

    public boolean isValidToken(String token) {
        try {
            JWT.require(getAlgorithm()).withIssuer(ISSUER).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    private Instant getExpiration(){
        return LocalDateTime.now().plusMinutes(20).toInstant(ZoneOffset.of("-03:00"));
    }
}
