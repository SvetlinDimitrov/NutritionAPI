package com.example.nutritionapi.config.security;

import java.security.Principal;

public class CustomPrincipal implements Principal {
    private String email;

    public CustomPrincipal(String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return email;
    }
}