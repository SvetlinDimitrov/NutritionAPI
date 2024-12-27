package com.example.nutrition_api.infrastructure.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.nutrition_api.domain.users.entity.UserEntity;
import com.example.nutrition_api.domain.users.service.UserServiceImp;
import com.example.nutrition_api.infrastructure.security.utils.EnvVariables;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtServiceImp {

    private final EnvVariables envVariables;
    private final UserServiceImp userService;

    public JwtServiceImp(EnvVariables envVariables, UserServiceImp userService) {
        this.envVariables = envVariables;
        this.userService = userService;
    }

    public String createJwtToken(Long userId) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(30, ChronoUnit.MINUTES)))
                .sign(Algorithm.HMAC256(envVariables.apiSecretKey()));
    }

    public DecodedJWT decodeToken(String token) {
        return JWT.require(Algorithm.HMAC256(envVariables.apiSecretKey()))
                .build()
                .verify(token);


    }

    public UserDetails convert(DecodedJWT token) {
        UserEntity user = userService.findById(Long.parseLong(token.getSubject()));
        return new User(user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserDetails().name())));

    }
}