package com.example.nutrition_api.domain.users.dto;

import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.enums.WorkoutState;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record UserUpdateRequest(
    @Size(min = 4 , max = 50)
    String username,

    BigDecimal kilograms,

    WorkoutState workoutState,

    Gender gender,

    BigDecimal height,

    Integer age
) {
}
