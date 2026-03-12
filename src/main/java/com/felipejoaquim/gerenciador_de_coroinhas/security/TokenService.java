package com.felipejoaquim.gerenciador_de_coroinhas.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;

@Service
public class TokenService {
    public String generateToken(Usuario user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("um grnade segredo");
            String token = JWT.create()
                                .withIssuer("gerenciador_coroinhas_pnsc")
                                .withSubject(user.getEmail())
                                .withExpiresAt(getExpirationTime())
                                .sign(algorithm);
            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("ERRO AO CRIAR O JWT - generateToken()!!!", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("um grnade segredo");
            return JWT.require(algorithm)
                        .withIssuer("gerenciador_coroinhas_pnsc")
                        .build()
                        .verify(token)
                        .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant getExpirationTime(){
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }
}
