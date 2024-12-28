package com.example.nutrition_api.infrastructure.security.refresh_token.entity;

import com.example.nutrition_api.domain.users.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private LocalDateTime expiryDate;

  @OneToOne
  private User user;
}
