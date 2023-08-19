package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.dtos.user.EditUserDto;
import com.example.nutritionapi.domain.dtos.user.LoginUserDto;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.domain.dtos.viewDtos.UserView;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.repos.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp {
    private final UserRepository userRepository;
    private final RecordServiceImp recordService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImp(UserRepository userRepository, RecordServiceImp recordService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.recordService = recordService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean notUsedEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public void register(RegisterUserDto userDto) {
        UserEntity entity = toUserEntity(userDto);

        fillUserWithCompleteDetails(entity);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("no user found with the given email"));
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("no user found with the given id"));
    }

    public boolean login(LoginUserDto userDto) {
        Optional<UserEntity> entity = userRepository.findByEmail(userDto.getEmail());
        return entity.isPresent() && passwordEncoder.matches(userDto.getPassword(), entity.get().getPassword());
    }

    public UserView getUserViewById(Long userId) {
        return new UserView(findById(userId));
    }
    public void editUserEntity(EditUserDto userDto, Long userId) {
        UserEntity user = findById(userId);

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getKilograms() != null) {
            user.setKilograms(userDto.getKilograms());
        }
        if (userDto.getWorkoutState() != null) {
            user.setWorkoutState(userDto.getWorkoutState());
        }
        if (userDto.getHeight() != null) {
            user.setHeight(userDto.getHeight());
        }
        if (userDto.getGender() != null) {
            user.setGender(userDto.getGender());
        }
        if (userDto.getAge() != null) {
            user.setAge(userDto.getAge());
        }

        fillUserWithCompleteDetails(user);
    }
    private void fillUserWithCompleteDetails(UserEntity entity) {
        if (entity.getKilograms() != null &&
                entity.getWorkoutState() != null &&
                entity.getGender() != null &&
                entity.getHeight() != null &&
                entity.getAge() != null &&
                (entity.getRecords() == null || entity.getRecords().isEmpty())) {
            entity.setUserDetails(UserDetails.COMPLETED);
            entity.getRecords().add(recordService.createRecord(entity));
        }
        userRepository.save(entity);
    }
    private UserEntity toUserEntity(RegisterUserDto userDto) {
        return new UserEntity()
                .setEmail(userDto.getEmail())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setUsername(userDto.getUsername())
                .setWorkoutState(userDto.getWorkoutState())
                .setKilograms(userDto.getKg())
                .setGender(userDto.getGender())
                .setHeight(userDto.getHeight())
                .setAge(userDto.getAge());
    }
}
