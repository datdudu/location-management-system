package br.com.pavscan.api.infra.security;

import br.com.pavscan.api.infra.persistence.Authentication.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserEntity userEntity) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            Instant expirationTime = expirationDateMinutes(50);

            System.out.println("Access Token Expiration Time: " + expirationTime);

            return JWT.create()
                    .withIssuer("API Pavscan")
                    .withSubject(userEntity.getUsername())
                    .withExpiresAt(Date.from(expirationTime)) // Ensure correct expiration time is set
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String generateRefreshToken(UserEntity userEntity) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            Instant expirationTime = expirationDateMinutes(30);

            System.out.println("Refresh Token Expiration Time: " + expirationTime);

            return JWT.create()
                    .withIssuer("API Pavscan")
                    .withSubject(userEntity.getUsername())
                    .withExpiresAt(Date.from(expirationTime)) // Ensure correct expiration time is set
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar refresh token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Pavscan")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!", exception);
        }
    }

    private Instant expirationDateHours(int hours) {
        return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant expirationDateMinutes(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.UTC); // Use UTC for standardization
    }
}
