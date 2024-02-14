package com.example.nutritionapi.domain.dtos.user;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;
import com.example.nutritionapi.exceptions.emailChecker.NotUsedEmailConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record RegisterUserDto(
        @NotBlank
        @Size(min = 4)
        String username,
        @NotUsedEmailConstraint
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 5)
        String password,

        BigDecimal kg,
        WorkoutState workoutState,
        Gender gender,
        BigDecimal height,
        Integer age
) {
}
