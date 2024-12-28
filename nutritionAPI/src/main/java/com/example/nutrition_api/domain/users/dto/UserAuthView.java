package com.example.nutrition_api.domain.users.dto;

import com.example.nutrition_api.domain.users.enums.UserDetails;

public record UserAuthView(
    Long id,
    String firstName,
    String lastName,
    String email,
    UserDetails details
) {

}
