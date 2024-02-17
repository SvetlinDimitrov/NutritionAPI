package com.api.reactive_nutritionapi.domain.dtos.viewDtos;

import com.api.reactive_nutritionapi.domain.entity.NutritionIntakeEntity;

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
  public static NutritionIntakeView toView(NutritionIntakeEntity entity){
    return new NutritionIntakeView(
        entity.getId(),
        entity.getNutrientName(),
        entity.getNutrientType(),
        entity.getLowerBoundIntake(),
        entity.getUpperBoundIntake(),
        entity.getDailyConsumed(),
        entity.getMeasurement(),
        entity.getRecordId());
  }
}
