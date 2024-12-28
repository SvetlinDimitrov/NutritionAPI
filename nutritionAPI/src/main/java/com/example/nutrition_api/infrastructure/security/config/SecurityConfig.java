package com.example.nutrition_api.infrastructure.security.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.example.nutrition_api.domain.users.enums.UserDetails;
import com.example.nutrition_api.infrastructure.security.filters.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
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
      "/nutritionApi/v1/user/register",
      "/nutritionApi/v1/user/login",
      "/nutritionApi/v1/electrolyte",
      "/nutritionApi/v1/electrolyte/{name}",
      "/nutritionApi/v1/macronutrient",
      "/nutritionApi/v1/macronutrient/{name}",
      "/nutritionApi/v1/vitamin",
      "/nutritionApi/v1/vitamin/{name}"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthorizationFilter filter) throws Exception {
    return httpSecurity
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .formLogin(withDefaults())
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2Login(withDefaults())
        .securityMatcher("/nutritionApi/**")
        .authorizeHttpRequests(
            request -> request
                .requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers("/nutritionApi/v1/records/**").hasRole(UserDetails.COMPLETED.name())
                .anyRequest().authenticated()
        )

//                ).logout(logout ->
//                        logout
//                                .logoutUrl("/logout")
//                                .logoutSuccessUrl("/login?logout")
//                                .invalidateHttpSession(true)
//                                .deleteCookies("JSESSIONID")
//                                .clearAuthentication(true)
//                                .permitAll())
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
