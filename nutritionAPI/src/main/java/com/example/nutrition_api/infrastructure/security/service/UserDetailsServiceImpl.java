package com.example.nutrition_api.infrastructure.security.service;

import com.example.nutrition_api.domain.users.service.UserServiceImp;
import com.example.nutrition_api.infrastructure.security.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserServiceImp userService;

  @Autowired
  public void setUserService(@Lazy UserServiceImp userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return new CustomUserDetails(userService.findByEmail(email));
  }
}
