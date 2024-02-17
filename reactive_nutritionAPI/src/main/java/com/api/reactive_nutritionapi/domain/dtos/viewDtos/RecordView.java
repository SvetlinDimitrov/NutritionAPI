package com.api.reactive_nutritionapi.domain.dtos.viewDtos;

import com.api.reactive_nutritionapi.domain.entity.NutritionIntakeEntity;
import com.api.reactive_nutritionapi.domain.entity.RecordEntity;

import java.math.BigDecimal;
import java.util.List;

public record RecordView(
        Long id,

        BigDecimal dailyCaloriesToConsume,
        Long userID,
        List<NutritionIntakeView> dailyIntakeViews
) {
  public static RecordView toView(RecordEntity entity , List<NutritionIntakeEntity> nutritionIntakeEntityList) {
    return new RecordView(
        entity.getId() ,
        entity.getDailyCalories(),
        entity.getUserId() ,
        nutritionIntakeEntityList.stream().map(NutritionIntakeView::toView).toList());
  }
  public static RecordView toView(RecordEntity entity) {
    return new RecordView(
        entity.getId() ,
        entity.getDailyCalories(),
        entity.getUserId() ,
        List.of());
  }
}
