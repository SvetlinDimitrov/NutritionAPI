package com.example.nutrition_api.domain.record.dto;

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
