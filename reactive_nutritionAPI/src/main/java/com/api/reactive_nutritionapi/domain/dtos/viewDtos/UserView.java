package com.api.reactive_nutritionapi.domain.dtos.viewDtos;

import com.api.reactive_nutritionapi.domain.constants.enums.Gender;
import com.api.reactive_nutritionapi.domain.constants.enums.UserDetails;
import com.api.reactive_nutritionapi.domain.constants.enums.WorkoutState;
import com.api.reactive_nutritionapi.domain.entity.UserEntity;

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
  public static UserView toView(UserEntity entity) {
    return new UserView(
        entity.getId(),
        entity.getUsername(),
        entity.getEmail(),
        entity.getKilograms(),
        entity.getHeight(),
        entity.getWorkoutState(),
        entity.getGender(),
        entity.getUserDetails(),
        entity.getAge()
    );
  }
}
