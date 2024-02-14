package com.example.nutritionapi.domain.entity;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "kilograms")
    private BigDecimal kilograms;
    @Column(name = "height")
    private BigDecimal height;
    @Column(name = "age")
    private Integer age;

    @Column(name = "workout_state")
    @Enumerated(EnumType.STRING)
    private WorkoutState workoutState;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "user_details")
    @Enumerated(EnumType.STRING)
    private UserDetails userDetails = UserDetails.NOT_COMPLETED;

    @OneToMany(mappedBy = "user" , cascade = {CascadeType.PERSIST,CascadeType.MERGE , CascadeType.REMOVE})
    private List<RecordEntity> records = new ArrayList<>();

    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public UserEntity setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public UserEntity setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public UserEntity setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(kilograms, user.kilograms) && Objects.equals(height, user.height) && Objects.equals(age, user.age) && workoutState == user.workoutState && gender == user.gender && userDetails == user.userDetails && Objects.equals(records, user.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, kilograms, height, age, workoutState, gender, userDetails, records);
    }
}
