package com.example.nutrition_api.domain.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserDto(@NotBlank @Email String email, @NotBlank String password) {
}
