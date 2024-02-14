package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;

import java.math.BigDecimal;

public record UserView(
        Long id,
        String username,
        String email,
        BigDecimal kilograms,
        BigDecimal height,
        WorkoutState workoutState,
        Gender gender,
        UserDetails userDetails,
        Integer age
) {
}
