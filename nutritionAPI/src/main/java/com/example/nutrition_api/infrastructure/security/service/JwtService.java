package com.example.nutrition_api.infrastructure.security.service;

import com.example.nutrition_api.infrastructure.security.dto.AuthenticationResponse;
import java.util.UUID;

public interface JwtService {

  AuthenticationResponse refreshToken(UUID token);

  AuthenticationResponse generateToken(String email);

  Boolean isAccessTokenValid(String token);

  String getEmailFromToken(String token);
}
