package com.example.nutritionapi.domain.dtos.user;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;
import com.example.nutritionapi.exceptions.emailChecker.NotUsedEmailConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class RegisterUserDto {

    @NotBlank
    @Size(min = 4)
    private String username;
    @NotUsedEmailConstraint
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;

    private BigDecimal kg;
    private WorkoutState workoutState;
    private Gender gender;
    private BigDecimal height;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public RegisterUserDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public RegisterUserDto setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public BigDecimal getKg() {
        return kg;
    }

    public RegisterUserDto setKg(BigDecimal kg) {
        this.kg = kg;
        return this;
    }

    public WorkoutState getWorkoutState() {
        return workoutState;
    }

    public RegisterUserDto setWorkoutState(WorkoutState workoutState) {
        this.workoutState = workoutState;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public RegisterUserDto setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegisterUserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
