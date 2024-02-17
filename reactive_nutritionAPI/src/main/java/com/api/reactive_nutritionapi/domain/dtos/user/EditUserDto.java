package com.api.reactive_nutritionapi.domain.dtos.user;

import com.api.reactive_nutritionapi.domain.constants.enums.Gender;
import com.api.reactive_nutritionapi.domain.constants.enums.WorkoutState;

import java.math.BigDecimal;

public record EditUserDto(
    String username,
    BigDecimal kilograms,
    WorkoutState workoutState,
    Gender gender,
    BigDecimal height,
    Integer age) {
}
