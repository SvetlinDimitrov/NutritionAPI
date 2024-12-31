package com.example.nutrition_api.infrastructure.security.service;

import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND;

import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.service.UserService;
import com.example.nutrition_api.infrastructure.exceptions.throwable.NotFoundException;
import com.example.nutrition_api.infrastructure.security.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserService userService;

  @Autowired
  public void setUserService(@Lazy UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return new CustomUserDetails(userService.findByEmail(email));
  }

  public User getLoggedInUser() {
    var authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new NotFoundException(USER_NOT_FOUND, "Logged in user not found");
    }

    var principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      return ((CustomUserDetails) principal).user();
    }

    throw new NotFoundException(USER_NOT_FOUND, "Logged in user not found");
  }
}
