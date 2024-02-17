package com.api.reactive_nutritionapi.domain.dtos;

import java.math.BigDecimal;

public record NutrientChangeDto(
        String name,
        BigDecimal measure
) {}
