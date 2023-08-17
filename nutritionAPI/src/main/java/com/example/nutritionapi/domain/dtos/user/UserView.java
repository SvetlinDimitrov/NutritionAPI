package com.example.nutritionapi.domain.dtos.user;

import com.example.nutritionapi.domain.constants.WorkoutState;
import com.example.nutritionapi.domain.entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class UserView extends User {

    private Long id;
    private String username;
    private String email;
    private BigDecimal kilograms;
    private WorkoutState workoutState;

    public UserView(String email, String password) {
        super(email, password, new ArrayList<>());
    }

    public UserView(UserEntity entity) {
        super(entity.getUsername(), entity.getPassword(), List.of());
        this.username = entity.getUsername();
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.kilograms = entity.getKilograms();
        this.workoutState = entity.getWorkoutState();
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
}
