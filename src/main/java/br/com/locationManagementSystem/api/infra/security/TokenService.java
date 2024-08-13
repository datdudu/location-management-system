package br.com.locationManagementSystem.api.infra.security;

import br.com.locationManagementSystem.api.infra.persistence.Authentication.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    String secret;

    public String generateToken(UserEntity userEntity) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            Instant expirationTime = expirationDateMinutes(50);

            System.out.println("Access Token Expiration Time: " + expirationTime);

            return JWT.create()
                    .withIssuer("API LocationManagementServer")
                    .withSubject(userEntity.getUsername())
                    .withExpiresAt(Date.from(expirationTime)) // Ensure correct expiration time is set
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error in generating a token", exception);
        }
    }

    public String generateRefreshToken(UserEntity userEntity) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            Instant expirationTime = expirationDateMinutes(30);

            System.out.println("Refresh Token Expiration Time: " + expirationTime);

            return JWT.create()
                    .withIssuer("API LocationManagementServer")
                    .withSubject(userEntity.getUsername())
                    .withExpiresAt(Date.from(expirationTime)) // Ensure correct expiration time is set
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error in generating a new refresh token", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API LocationManagementServer")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT Invalid or Expired! Token JWT", exception);
        }
    }

    private Instant expirationDateHours(int hours) {
        return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant expirationDateMinutes(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.UTC); // Use UTC for standardization
    }
}
