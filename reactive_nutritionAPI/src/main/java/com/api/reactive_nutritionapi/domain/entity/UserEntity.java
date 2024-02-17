package com.api.reactive_nutritionapi.domain.entity;

import com.api.reactive_nutritionapi.domain.constants.enums.Gender;
import com.api.reactive_nutritionapi.domain.constants.enums.UserDetails;
import com.api.reactive_nutritionapi.domain.constants.enums.WorkoutState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;


@Table(name = "users")
@Getter
public class UserEntity {
  @Id
  @Setter
  private Long id;

  @Column("username")
  private String username;

  @Column("email")
  @Setter
  private String email;

  @Column("password")
  @Setter
  private String password;

  @Column("kilograms")
  private BigDecimal kilograms;
  @Column("height")
  private BigDecimal height;
  @Column("age")
  private Integer age;

  @Column("workout_state")
  private WorkoutState workoutState;

  @Column("gender")
  private Gender gender;

  @Column("user_details")
  @Setter
  private UserDetails userDetails = UserDetails.NOT_COMPLETED;

  public void setUsername(String username) {
    if (username != null && username.length() > 4 && !username.isBlank()) {
      this.username = username;
    }
  }
  public void setKilograms(BigDecimal kilograms) {
    if (kilograms != null && kilograms.compareTo(BigDecimal.ZERO) > 0) {
      this.kilograms = kilograms;
    }
  }
  public void setHeight(BigDecimal height) {
    if (height != null && height.compareTo(BigDecimal.ZERO) > 0) {
      this.height = height;
    }
  }
  public void setAge(Integer age) {
    if (age != null) {
      this.age = age;
    }
  }
  public void setWorkoutState(WorkoutState workoutState) {
    if (workoutState != null) {
      this.workoutState = workoutState;
    }
  }
  public void setGender(Gender gender) {
    if (gender != null) {
      this.gender = gender;
    }
  }
}
