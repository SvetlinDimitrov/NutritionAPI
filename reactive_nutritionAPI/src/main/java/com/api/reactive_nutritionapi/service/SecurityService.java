package com.api.reactive_nutritionapi.service;

import com.api.reactive_nutritionapi.config.security.UserPrincipal;
import com.api.reactive_nutritionapi.config.security.jwt.JwtTokenProvider;
import com.api.reactive_nutritionapi.domain.constants.enums.UserDetails;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.JwtResponse;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.UserView;
import com.api.reactive_nutritionapi.domain.entity.UserEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SecurityService {
  private final JwtTokenProvider tokenProvider;
  private final ReactiveAuthenticationManager authenticationManager;

  public SecurityService(JwtTokenProvider tokenProvider, ReactiveAuthenticationManager authenticationManager) {
    this.tokenProvider = tokenProvider;
    this.authenticationManager = authenticationManager;
  }

  public Mono<JwtResponse> authAndGenerateJwtToken(UserEntity entity) {
    return Mono.just(entity)
        .map(user -> {
          UserPrincipal principal = new UserPrincipal(user);
          PreAuthenticatedAuthenticationToken auth = new PreAuthenticatedAuthenticationToken(principal, null, principal.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(auth);
          UserView view = UserView.toView(user);
          String token = tokenProvider.createToken(principal);
          return new JwtResponse(view, token);
        });
  }
}
