package com.example.nutritionapi.domain.dtos.user;

import com.example.nutritionapi.exceptions.emailChecker.NotUsedEmailConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterUserDto {

    @NotBlank
    private String username;
    @NotUsedEmailConstraint
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;

    public String getUsername() {
        return username;
    }

    public RegisterUserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
