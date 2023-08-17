package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.dtos.user.UserView;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean notUsedEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public void register(RegisterUserDto userDto) {
        userRepository.save(toUserEntity(userDto));
    }

    private UserEntity toUserEntity(RegisterUserDto userDto){
        return new UserEntity()
                .setEmail(userDto.getEmail())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setUsername(userDto.getUsername());
    }

    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserView::new)
                .orElseThrow(() -> new UsernameNotFoundException("no user found with the given email"));
    }

    public UserEntity findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("no user found with the given id"));
    }
}
