package com.example.nutritionapi.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtUtil {

    @Value("${security.api-secret-key}")
    private String secret;

    private final UserServiceImp userService;

    public JwtUtil(UserServiceImp userService) {
        this.userService = userService;
    }

    public String createJwtToken(Long userId){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1 , ChronoUnit.DAYS)))
                .sign(Algorithm.HMAC256(secret));
    }

    public DecodedJWT decodeToken(String token){
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);
    }

    public UserPrincipal convert(DecodedJWT token){
        UserEntity user = userService.findById(Long.parseLong(token.getSubject()));
        return new UserPrincipal(user);

    }
}