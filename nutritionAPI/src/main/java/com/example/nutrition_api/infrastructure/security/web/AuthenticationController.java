package com.example.nutrition_api.infrastructure.security.web;

import com.example.nutrition_api.infrastructure.open_ai.AuthenticationControllerDocs;
import com.example.nutrition_api.infrastructure.security.dto.AuthenticationRequest;
import com.example.nutrition_api.infrastructure.security.dto.AuthenticationResponse;
import com.example.nutrition_api.infrastructure.security.service.JwtService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationControllerDocs {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponse createAuthenticationToken(
      @Valid @RequestBody AuthenticationRequest authenticationRequest
  ) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.email(),
            authenticationRequest.password())
    );

    return jwtService.generateToken(authenticationRequest.email());
  }

  @PostMapping("/refresh/{token}")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponse refreshToken(@PathVariable UUID token) {
    return jwtService.refreshToken(token);
  }
}