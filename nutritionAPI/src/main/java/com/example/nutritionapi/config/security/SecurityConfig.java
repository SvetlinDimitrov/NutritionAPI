package com.example.nutritionapi.config.security;

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


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity , JwtAuthorizationFilter filter) throws Exception {
        return httpSecurity
                .addFilterBefore(filter , UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityMatcher("/nutritionApi/**")
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers(
                                        "/nutritionApi/user/register",
                                        "/nutritionApi/electrolyte/**",
                                        "/nutritionApi/macronutrient/**",
                                        "/nutritionApi/vitamin/**",
                                        "/nutritionApi/user/login"
                                ).permitAll()
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
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
