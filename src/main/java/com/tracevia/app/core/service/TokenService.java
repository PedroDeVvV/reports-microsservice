package com.tracevia.app.core.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.tracevia.app.core.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret-key}")
    private String secret;

    public String generateToken(User user) {

        try {

            var algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("Report API")
                    .withSubject(user.getLogin())
                    .withExpiresAt(limitDate())
                    .withClaim("id", user.getId())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {

            throw new RuntimeException("Erro ao gerar token JWT: ", exception);
        }
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("Report API")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (RuntimeException e) {
            throw new RuntimeException("Token JWT inválido");
        }
    }

    private Instant limitDate() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }

}
