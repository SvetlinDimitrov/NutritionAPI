package com.api.reactive_nutritionapi.domain.dtos.viewDtos;

import java.time.LocalDateTime;

public record JwtToken (String value , LocalDateTime expiresIn){}
