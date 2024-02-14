package com.example.nutritionapi.domain.dtos.viewDtos;

import java.math.BigDecimal;
import java.util.List;

public record RecordView(
        Long day,
        List<NutritionIntakeView> dailyIntakeViews,
        BigDecimal dailyCaloriesToConsume,
        Long userID,
        String userName
) {
}
