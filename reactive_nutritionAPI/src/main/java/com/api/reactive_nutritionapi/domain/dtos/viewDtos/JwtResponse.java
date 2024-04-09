package com.api.reactive_nutritionapi.domain.dtos.viewDtos;

public record JwtResponse(UserView userView, JwtToken accessToken) {}
