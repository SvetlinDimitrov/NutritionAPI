package com.example.nutritionapi.domain.dtos.user;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;

import java.math.BigDecimal;

public record EditUserDto(String username, BigDecimal kilograms, WorkoutState workoutState, Gender gender,
                          BigDecimal height, Integer age) {
}
