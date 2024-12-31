package com.example.nutrition_api.domain.users.dto;

import com.example.nutrition_api.domain.users.annotations.NotBlankIfPresent;
import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.enums.WorkoutState;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record UserUpdateRequest(
  @Size(min = 4, max = 50)
  @NotBlankIfPresent
  String username,

  @DecimalMin(value = "0.0", inclusive = false)
  BigDecimal kilograms,

  WorkoutState workoutState,

  Gender gender,

  @DecimalMin(value = "0.0", inclusive = false)
  BigDecimal height,

  @Min(0)
  @Max(120)
  Integer age
) {
}