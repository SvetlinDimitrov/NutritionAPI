package com.example.nutrition_api.domain.record.service;

import com.example.nutrition_api.domain.record.dto.NutrientUpdateRequest;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.entity.NutritionIntake;
import com.example.nutrition_api.domain.record.entity.Record;
import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.enums.WorkoutState;
import java.math.BigDecimal;
import java.util.List;

public interface NutrientIntakeService {

  List<NutritionIntake> create(Gender gender, BigDecimal caloriesPerDay,
      WorkoutState state, Record record);

  NutritionIntakeView update(NutrientUpdateRequest dto, Long recordId);
}
