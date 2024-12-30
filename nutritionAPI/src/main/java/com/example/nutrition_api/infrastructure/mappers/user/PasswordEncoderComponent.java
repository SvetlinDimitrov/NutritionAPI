package com.example.nutrition_api.infrastructure.mappers.user;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderComponent {

  private final PasswordEncoder passwordEncoder;

  @Named("encodePassword")
  protected String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}