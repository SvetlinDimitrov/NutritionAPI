package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;
import com.example.nutritionapi.domain.entity.UserEntity;

import java.math.BigDecimal;
import java.util.Objects;

public class UserView {
    private Long id;

    private String username;

    private String email;

    private BigDecimal kilograms;
    private BigDecimal height;

    private WorkoutState workoutState;

    private Gender gender;

    private UserDetails userDetails;
    private Integer age;

    public UserView() {}

    public UserView(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.kilograms = userEntity.getKilograms();
        this.workoutState = userEntity.getWorkoutState();
        this.gender = userEntity.getGender();
        this.userDetails = userEntity.getUserDetails();
        this.height = userEntity.getHeight();
        this.age = userEntity.getAge();

    }

    public Integer getAge() {
        return age;
    }

    public UserView setAge(Integer age) {
        this.age = age;
        return this;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public UserView setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserView setEmail(String email) {
        this.email = email;
        return this;
    }

    public BigDecimal getKilograms() {
        return kilograms;
    }

    public UserView setKilograms(BigDecimal kilograms) {
        this.kilograms = kilograms;
        return this;
    }

    public WorkoutState getWorkoutState() {
        return workoutState;
    }

    public UserView setWorkoutState(WorkoutState workoutState) {
        this.workoutState = workoutState;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public UserView setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public UserView setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserView userView = (UserView) o;
        return Objects.equals(id, userView.id) && Objects.equals(username, userView.username) && Objects.equals(email, userView.email) && Objects.equals(kilograms, userView.kilograms) && Objects.equals(height, userView.height) && workoutState == userView.workoutState && gender == userView.gender && userDetails == userView.userDetails && Objects.equals(age, userView.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, kilograms, height, workoutState, gender, userDetails, age);
    }
}
