package com.example.nutrition_api.infrastructure.security.refresh_token.dto;

import java.time.LocalDateTime;

public record RefreshTokenView(String token, LocalDateTime expirationTime) {

}
