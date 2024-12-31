package com.example.nutrition_api.infrastructure.security.service;

import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.REFRESH_TOKEN_EXPIRED;
import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.REFRESH_TOKEN_NOT_FOUND;

import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.service.UserService;
import com.example.nutrition_api.infrastructure.exceptions.throwable.BadRequestException;
import com.example.nutrition_api.infrastructure.exceptions.throwable.NotFoundException;
import com.example.nutrition_api.infrastructure.mappers.RefreshTokenMapper;
import com.example.nutrition_api.infrastructure.security.config.JwtConfig;
import com.example.nutrition_api.infrastructure.security.dto.AccessTokenView;
import com.example.nutrition_api.infrastructure.security.dto.AuthenticationResponse;
import com.example.nutrition_api.infrastructure.security.refresh_token.dto.RefreshTokenView;
import com.example.nutrition_api.infrastructure.security.refresh_token.entity.RefreshToken;
import com.example.nutrition_api.infrastructure.security.refresh_token.repository.RefreshTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

  private final RefreshTokenRepository refreshTokenRepository;
  private final UserService userService;
  private final RefreshTokenMapper refreshTokenMapper;
  private final JwtConfig jwtConfig;

  public AuthenticationResponse refreshToken(UUID token) {
    RefreshToken refreshToken = refreshTokenRepository
        .findById(token)
        .orElseThrow(() -> new NotFoundException(REFRESH_TOKEN_NOT_FOUND, token.toString()));

    if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
      refreshTokenRepository.delete(refreshToken);
      throw new BadRequestException(REFRESH_TOKEN_EXPIRED);
    }

    var accessTokenView = generateAccessToken(refreshToken.getUser());
    var refreshTokenView = refreshTokenMapper.toView(refreshToken);

    return new AuthenticationResponse(accessTokenView, refreshTokenView);
  }

  public AuthenticationResponse generateToken(String email) {
    var user = userService.findByEmail(email);

    var accessTokenView = generateAccessToken(user);
    var refreshTokenView = generateRefreshToken(user);

    return new AuthenticationResponse(accessTokenView, refreshTokenView);
  }

  public Boolean isAccessTokenValid(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(jwtConfig.getSecretKey())
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("JWT token is malformed: {}", e.getMessage());
    } catch (SignatureException e) {
      log.error("JWT token signature validation failed: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT token is illegal or inappropriate: {}", e.getMessage());
    }
    return false;
  }

  public String getEmailFromToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(jwtConfig.getSecretKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  private AccessTokenView generateAccessToken(User user) {
    var currentDate = new Date();
    var expirationDate = new Date(currentDate.getTime() + jwtConfig.getJwtDuration());

    String token = Jwts.builder()
        .setSubject(user.getEmail())
        .claim("userId", user.getId())
        .claim("details", user.getUserDetails())
        .setIssuedAt(currentDate)
        .setExpiration(expirationDate)
        .signWith(jwtConfig.getSecretKey(), SignatureAlgorithm.HS256)
        .compact();

    return new AccessTokenView(token);
  }

  private RefreshTokenView generateRefreshToken(User user) {
    var userRefreshToken = user.getRefreshToken();

    if (userRefreshToken != null) {
      refreshTokenRepository.delete(userRefreshToken);
    }

    var refreshToken = new RefreshToken();
    var expiryDate = LocalDateTime.now().plusSeconds(jwtConfig.getRefreshTokenDuration() / 1000);

    refreshToken.setUser(user);
    refreshToken.setExpiryDate(expiryDate);

    return refreshTokenMapper.toView(refreshTokenRepository.save(refreshToken));
  }
}