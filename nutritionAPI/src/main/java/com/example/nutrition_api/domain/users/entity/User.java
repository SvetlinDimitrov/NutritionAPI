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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"records", "refreshToken"})
@ToString(exclude = {"records", "refreshToken"})
@Builder
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
}
