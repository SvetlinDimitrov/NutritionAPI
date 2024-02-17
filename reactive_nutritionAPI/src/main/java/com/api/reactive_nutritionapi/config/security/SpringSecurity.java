package com.api.reactive_nutritionapi.config.security;

import com.api.reactive_nutritionapi.config.security.jwt.JwtTokenAuthenticationFilter;
import com.api.reactive_nutritionapi.config.security.jwt.JwtTokenProvider;
import com.api.reactive_nutritionapi.domain.constants.enums.UserDetails;
import com.api.reactive_nutritionapi.repos.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@EnableWebFluxSecurity
@Configuration
public class SpringSecurity {

  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                                    JwtTokenProvider tokenProvider,
                                                    ReactiveAuthenticationManager reactiveAuthenticationManager) {
    return http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .cors(ServerHttpSecurity.CorsSpec::disable)
        .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
        .authenticationManager(reactiveAuthenticationManager)
        .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        .authorizeExchange(
            request -> request
                .pathMatchers(
                    "/nutritionApi/user/register",
                    "/nutritionApi/user/login",
                    "/nutritionApi/electrolyte",
                    "/nutritionApi/electrolyte/{name}",
                    "/nutritionApi/macronutrient",
                    "/nutritionApi/macronutrient/{name}",
                    "/nutritionApi/vitamin",
                    "/nutritionApi/vitamin/{name}"
                ).permitAll()
                .pathMatchers("/nutritionApi/records/**").hasRole(UserDetails.COMPLETED.name())
                .anyExchange().authenticated()
        )
        .addFilterAt(new JwtTokenAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)
        .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public ReactiveUserDetailsService userDetailsService(UserRepository users) {
    return email -> users.findByEmail(email)
        .map(UserPrincipal::new);
  }

  @Bean
  public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
                                                                     PasswordEncoder passwordEncoder) {
    var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    authenticationManager.setPasswordEncoder(passwordEncoder);
    return authenticationManager;
  }
}
