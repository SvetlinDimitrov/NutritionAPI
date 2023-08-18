package com.example.nutritionapi.config.security;

import com.example.nutritionapi.domain.constants.UserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthorizationFilter filter) throws Exception {
        return httpSecurity
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityMatcher("/nutritionApi/**")
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers(
                                        "/nutritionApi/user/register",
                                        "/nutritionApi/user/login",
                                        "/nutritionApi/electrolyte",
                                        "/nutritionApi/electrolyte/{name}",
                                        "/nutritionApi/macronutrient",
                                        "/nutritionApi/macronutrient/{name}",
                                        "/nutritionApi/vitamin",
                                        "/nutritionApi/vitamin/{name}"

                                ).permitAll()
                                .requestMatchers("/nutritionApi/records/**").hasRole(UserDetails.COMPLETED.name())
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
