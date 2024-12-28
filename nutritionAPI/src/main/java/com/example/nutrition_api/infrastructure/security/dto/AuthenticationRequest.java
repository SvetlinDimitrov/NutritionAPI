package com.example.nutrition_api.infrastructure.security.dto;

import com.example.nutrition_api.infrastructure.security.annotations.ValidPassword;
import jakarta.validation.constraints.Email;

public record AuthenticationRequest(

    @Email(message = "Email must be valid")
    String email,
    @ValidPassword
    String password
) {

}
