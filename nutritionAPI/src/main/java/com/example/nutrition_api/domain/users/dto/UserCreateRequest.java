package com.example.nutrition_api.domain.users.dto;

import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.enums.WorkoutState;
import com.example.nutrition_api.infrastructure.exceptions.emailChecker.NotUsedEmailConstraint;
import com.example.nutrition_api.infrastructure.security.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record UserCreateRequest(
        @NotBlank
        @Size(min = 4 , max = 50)
        String username,

        @NotUsedEmailConstraint
        @NotBlank
        @Email
        @Size(min = 4 , max = 250)
        String email,

        @NotBlank
        @ValidPassword
        String password,

        BigDecimal kg,

        WorkoutState workoutState,

        Gender gender,

        BigDecimal height,

        Integer age
) {
}
