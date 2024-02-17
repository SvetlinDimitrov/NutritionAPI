package com.api.reactive_nutritionapi.domain.dtos.user;

import com.api.reactive_nutritionapi.domain.constants.enums.Gender;
import com.api.reactive_nutritionapi.domain.constants.enums.WorkoutState;
import com.api.reactive_nutritionapi.domain.entity.UserEntity;

import java.math.BigDecimal;

public record RegisterUserDto(
    String username,
    String email,
    String password,
    BigDecimal kg,
    WorkoutState workoutState,
    Gender gender,
    BigDecimal height,
    Integer age
) {
  public UserEntity toUserEntity() {
    UserEntity user = new UserEntity();
    user.setEmail(email());
    user.setPassword(password());
    user.setUsername(username());
    user.setWorkoutState(workoutState());
    user.setKilograms(kg());
    user.setGender(gender());
    user.setHeight(height());
    user.setAge(age());
    return user;
  }
}
