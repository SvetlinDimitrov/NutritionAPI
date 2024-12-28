package com.example.nutrition_api.infrastructure.security.config;

import com.example.nutrition_api.infrastructure.security.filters.JwtRequestFilter;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private static final String[] WHITE_LIST = {
      "/v2/api-docs",
      "/v3/api-docs",
      "/v3/api-docs/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui/**",
      "/webjars/**",
      "/swagger-ui.html",
      "/api/v1/user/register",
      "/api/v1/auth",
      "/api/v1/electrolyte",
      "/api/v1/electrolyte/{name}",
      "/api/v1/macronutrient",
      "/api/v1/macronutrient/{name}",
      "/api/v1/vitamin",
      "/api/v1/vitamin/{name}"
  };
  @Value("${cors.allowed-origins}")
  private List<String> allowedOrigins;
  private final JwtRequestFilter jwtRequestFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> corsConfigurationSource())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers(WHITE_LIST).permitAll()
                .anyRequest().authenticated()
        )
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(allowedOrigins);
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }
}
