package com.example.nutrition_api.domain.record.dto;

import java.math.BigDecimal;
import java.util.List;

public record RecordView(
        Long id,

        List<NutritionIntakeView> dailyIntakeViews,

        BigDecimal dailyCaloriesToConsume,

        Long userID,

        String userName
) {
}
