package com.example.nutritionapi.config.security;

import com.example.nutritionapi.service.UserServiceImp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsImp implements UserDetailsService {

    private final UserServiceImp userService;

    public UserDetailsImp(UserServiceImp userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findByEmail(email);
    }
}
