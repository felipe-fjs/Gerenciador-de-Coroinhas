package com.felipejoaquim.gerenciador_de_coroinhas.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
    private String secret = "asdhbahsbdhias-asdjbajdbaosdjba-khqeojb12398&@*31yna";
    private Algorithm alg = Algorithm.HMAC256(this.secret);

    public String generateToken(Usuario usuario) {

        try {
            return JWT.create()
                        .withIssuer("gerenciador-de-coroinhas")
                        .withSubject(usuario.getEmail())
                        .withExpiresAt(getExpiration())
                        .sign(alg);

        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar Token", e);
        }
    }

    public String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header == null) {
            return null;
        }
        String token = header.replace("Bearer ", "");
        return token;
    }

    public String getEmail(String token){
        try {
            return JWT.require(this.alg)
                        .withIssuer("gerenciador-de-coroinhas")
                        .build()
                        .verify(token)
                        .getSubject();

        } catch (RuntimeException e) {
            return "";
        }
    }

    private Instant getExpiration(){
        return LocalDateTime.now().plusMinutes(20).toInstant(ZoneOffset.of("-03:00"));
    }
}
