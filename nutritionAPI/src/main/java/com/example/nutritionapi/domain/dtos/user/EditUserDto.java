package com.example.nutritionapi.domain.dtos.user;

import com.example.nutritionapi.domain.constants.Gender;
import com.example.nutritionapi.domain.constants.WorkoutState;

import java.math.BigDecimal;

public class EditUserDto {
    private String username;
    private String email;
    private BigDecimal kilograms;
    private WorkoutState workoutState;
    private Gender gender;
    private BigDecimal height;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public EditUserDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public EditUserDto setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public EditUserDto setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public EditUserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EditUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public BigDecimal getKilograms() {
        return kilograms;
    }

    public EditUserDto setKilograms(BigDecimal kilograms) {
        this.kilograms = kilograms;
        return this;
    }

    public WorkoutState getWorkoutState() {
        return workoutState;
    }

    public EditUserDto setWorkoutState(WorkoutState workoutState) {
        this.workoutState = workoutState;
        return this;
    }
}
