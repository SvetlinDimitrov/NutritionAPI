package com.example.nutrition_api.infrastructure.security.dto;

import com.example.nutrition_api.infrastructure.security.refresh_token.dto.RefreshTokenView;

public record AuthenticationResponse(AccessTokenView accessToken, RefreshTokenView refreshToken) {

}
