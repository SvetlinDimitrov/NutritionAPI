package com.example.nutritionapi.domain.entity;

import com.example.nutritionapi.domain.constants.WorkoutState;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private BigDecimal kilograms;

    @Column
    @Enumerated(EnumType.STRING)
    private WorkoutState workoutState;

    @OneToMany(mappedBy = "user")
    private List<RecordEntity> records;

    public BigDecimal getKilograms() {
        return kilograms;
    }

    public UserEntity setKilograms(BigDecimal kilograms) {
        this.kilograms = kilograms;
        return this;
    }

    public WorkoutState getWorkoutState() {
        return workoutState;
    }

    public UserEntity setWorkoutState(WorkoutState workoutState) {
        this.workoutState = workoutState;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public List<RecordEntity> getRecords() {
        return records;
    }

    public UserEntity setRecords(List<RecordEntity> records) {
        this.records = records;
        return this;
    }

    public UserEntity setUsername(String name) {
        this.username = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
