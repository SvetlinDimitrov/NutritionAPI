package com.example.nutrition_api.domain.users.entity;

import com.example.nutrition_api.domain.record.entity.Record;
import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.enums.UserDetails;
import com.example.nutrition_api.domain.users.enums.WorkoutState;
import com.example.nutrition_api.infrastructure.security.refresh_token.entity.RefreshToken;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
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

  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private List<Record> records = new ArrayList<>();

  @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private RefreshToken refreshToken;

  public User setAge(Integer age) {
    this.age = age;
    return this;
  }

  public User setHeight(BigDecimal height) {
    this.height = height;
    return this;
  }

  public User setGender(Gender gender) {
    this.gender = gender;
    return this;
  }

  public User setKilograms(BigDecimal kilograms) {
    this.kilograms = kilograms;
    return this;
  }

  public User setWorkoutState(WorkoutState workoutState) {
    this.workoutState = workoutState;
    return this;
  }

  public User setRecords(List<Record> records) {
    this.records = records;
    return this;
  }

  public User setUsername(String name) {
    this.username = name;
    return this;
  }

  public User setRefreshToken(RefreshToken refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
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

  public User setUserDetails(UserDetails userDetails) {
    this.userDetails = userDetails;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o == null || getClass() != o.getClass()) {return false;}
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(kilograms, user.kilograms) && Objects.equals(height, user.height) && Objects.equals(age, user.age)
        && workoutState == user.workoutState && gender == user.gender && userDetails == user.userDetails && Objects.equals(records, user.records);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, kilograms, height, age, workoutState, gender, userDetails, records);
  }
}
