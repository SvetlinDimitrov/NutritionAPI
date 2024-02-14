package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.dtos.user.EditUserDto;
import com.example.nutritionapi.domain.dtos.user.LoginUserDto;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.domain.dtos.viewDtos.UserView;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.repos.UserRepository;
import com.example.nutritionapi.utils.ViewConverter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImp {
    private final UserRepository userRepository;
    private final RecordServiceImp recordService;
    private final PasswordEncoder passwordEncoder;
    private final ViewConverter converter;


    public UserServiceImp(UserRepository userRepository, RecordServiceImp recordService, PasswordEncoder passwordEncoder, ViewConverter converter) {
        this.userRepository = userRepository;
        this.recordService = recordService;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
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

    public UserView findByEmailView(String email) {
        return converter.toView(findByEmail(email));
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("no user found with the given id"));
    }

    public boolean login(LoginUserDto userDto) {
        Optional<UserEntity> entity = userRepository.findByEmail(userDto.email());
        return entity.isPresent() && passwordEncoder.matches(userDto.password(), entity.get().getPassword());
    }

    public UserView getUserViewById(Long userId) {
        return converter.toView(findById(userId));
    }
    public void editUserEntity(EditUserDto userDto, Long userId) {
        UserEntity user = findById(userId);

        if (userDto.username() != null && userDto.username().length() > 4 && !userDto.username().isBlank()) {
            user.setUsername(userDto.username());
        }
        if (userDto.kilograms() != null) {
            user.setKilograms(userDto.kilograms());
        }
        if (userDto.workoutState() != null) {
            user.setWorkoutState(userDto.workoutState());
        }
        if (userDto.height() != null) {
            user.setHeight(userDto.height());
        }
        if (userDto.gender() != null) {
            user.setGender(userDto.gender());
        }
        if (userDto.age() != null) {
            user.setAge(userDto.age());
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
                .setEmail(userDto.email())
                .setPassword(passwordEncoder.encode(userDto.password()))
                .setUsername(userDto.username())
                .setWorkoutState(userDto.workoutState())
                .setKilograms(userDto.kg())
                .setGender(userDto.gender())
                .setHeight(userDto.height())
                .setAge(userDto.age());
    }
}
