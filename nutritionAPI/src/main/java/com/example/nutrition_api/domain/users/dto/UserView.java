package com.example.nutrition_api.domain.users.dto;

import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.enums.UserDetails;
import com.example.nutrition_api.domain.users.enums.WorkoutState;
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
