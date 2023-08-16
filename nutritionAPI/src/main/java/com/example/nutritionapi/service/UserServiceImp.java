package com.example.nutritionapi.service;

import com.example.nutritionapi.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
