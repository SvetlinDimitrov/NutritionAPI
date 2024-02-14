package com.example.nutritionapi.domain.dtos.viewDtos;

import java.math.BigDecimal;

public record NutritionIntakeView(
        Long id,

        String nutrientName,

        String nutrientType,

        BigDecimal lowerBoundIntake,

        BigDecimal upperBoundIntake,
        BigDecimal dailyConsumed,

        String measurement,

        Long recordId
) {
}
