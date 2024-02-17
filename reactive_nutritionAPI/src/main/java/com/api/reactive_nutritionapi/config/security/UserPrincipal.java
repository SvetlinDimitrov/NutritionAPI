package com.api.reactive_nutritionapi.config.security;

import com.api.reactive_nutritionapi.domain.constants.enums.UserDetails;
import com.api.reactive_nutritionapi.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@Setter
public class UserPrincipal extends User {

  private final Long id;
  private final String email;
  private final String password;
  private final UserDetails userDetails;


  public UserPrincipal(UserEntity user) {
    super(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserDetails().name())));
    this.id = user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.userDetails = user.getUserDetails();
  }
}
