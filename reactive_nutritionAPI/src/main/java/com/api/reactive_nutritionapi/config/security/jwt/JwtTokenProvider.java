package com.api.reactive_nutritionapi.config.security.jwt;


import com.api.reactive_nutritionapi.config.security.UserPrincipal;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.UserView;
import com.api.reactive_nutritionapi.domain.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import static java.util.stream.Collectors.joining;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
  private static final String AUTHORITIES_KEY = "roles";
  private final static String SECRET_WORD = "rzxlszyykpbgqcflzxsqcysyhljt";
  private final static long VALID_DURATION_TIME_MINUTES = 3600000;
  private SecretKey secretKey;

  @PostConstruct
  public void init() {
    var secret = Base64
        .getEncoder()
        .encodeToString(SECRET_WORD.getBytes());
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String createToken(UserPrincipal principal) {

    String username = principal.getEmail();

    Collection<? extends GrantedAuthority> authorities = principal
        .getAuthorities();

    Claims claims = Jwts.claims().setSubject(username);
    if (!authorities.isEmpty()) {
      claims.put(AUTHORITIES_KEY, authorities.stream()
          .map(GrantedAuthority::getAuthority).collect(joining(",")));
    }
    Date now = new Date();
    Date validity = new Date(now.getTime() + VALID_DURATION_TIME_MINUTES);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(this.secretKey, SignatureAlgorithm.HS256)
        .compact();

  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(this.secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody();

    Object authoritiesClaim = claims.get(AUTHORITIES_KEY);

    Collection<? extends GrantedAuthority> authorities = authoritiesClaim == null
        ? AuthorityUtils.NO_AUTHORITIES
        : AuthorityUtils
        .commaSeparatedStringToAuthorityList(authoritiesClaim.toString());

    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder()
          .setSigningKey(this.secretKey)
          .build()
          .parseClaimsJws(token);
      // parseClaimsJws will check expiration date. No need do here.
      log.info("expiration date: {}", claims.getBody().getExpiration());
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      log.info("Invalid JWT token: {}", e.getMessage());
      log.trace("Invalid JWT token trace.", e);
    }
    return false;
  }
}
