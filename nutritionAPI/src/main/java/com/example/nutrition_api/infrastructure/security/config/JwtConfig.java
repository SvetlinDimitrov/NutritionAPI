package com.example.nutrition_api.infrastructure.security.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtConfig {

  private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  @Value("${refresh-token.expiration-time}")
  private long refreshTokenDuration;
  @Value("${jwt.expiration-time}")
  private long jwtDuration;
}
