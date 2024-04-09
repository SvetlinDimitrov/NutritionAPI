package com.api.reactive_nutritionapi.service;

import com.api.reactive_nutritionapi.domain.constants.enums.Gender;
import com.api.reactive_nutritionapi.domain.constants.enums.UserDetails;
import com.api.reactive_nutritionapi.domain.constants.enums.WorkoutState;
import com.api.reactive_nutritionapi.domain.dtos.user.EditUserDto;
import com.api.reactive_nutritionapi.domain.dtos.user.LoginUserDto;
import com.api.reactive_nutritionapi.domain.dtos.user.RegisterUserDto;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.JwtResponse;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.UserView;
import com.api.reactive_nutritionapi.domain.entity.UserEntity;
import com.api.reactive_nutritionapi.exceptions.UserException;
import com.api.reactive_nutritionapi.repos.UserRepository;
import com.api.reactive_nutritionapi.utils.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class UserServiceImp {
  private final UserRepository userRepository;
  private final RecordServiceImp recordService;
  private final SecurityService jwtService;
  private final PasswordEncoder passwordEncoder;


  public UserServiceImp(UserRepository userRepository, RecordServiceImp recordService, SecurityService jwtService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.recordService = recordService;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
  }

  public Mono<Void> register(Mono<RegisterUserDto> userDto) {
    return
        userDto
            .flatMap(dto -> UserValidator.validateUsername(Mono.just(dto)))
            .flatMap(dto -> UserValidator.validateEmail(Mono.just(dto)))
            .flatMap(dto -> UserValidator.validatePassword(Mono.just(dto)))
            .flatMap(dto -> userRepository.findByEmail(dto.email())
                    .flatMap(existingUser -> Mono.error(new UserException("User with email " + dto.email() + " already exists")))
                    .switchIfEmpty(Mono.just(dto.toUserEntity())
                .flatMap(this::fillUserWithCompleteDetails)
                .map(user -> {
                  user.setPassword(passwordEncoder.encode(user.getPassword()));
                  return user;
                })
                .flatMap(userRepository::save)
            )
            )
            .then();
  }

  public Mono<JwtResponse> login(LoginUserDto userDto) {
    return userRepository
        .findByEmail(userDto.email())
        .switchIfEmpty(Mono.error(new UserException("no user found with the given email")))
        .filter(user -> passwordEncoder.matches(userDto.password(), user.getPassword()))
        .switchIfEmpty(Mono.error(new UserException("wrong credentials")))
        .flatMap(jwtService::authAndGenerateJwtToken);
  }

  public Mono<UserView> getUserViewByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .switchIfEmpty(Mono.error(new UserException("No user found")))
        .map(UserView::toView);
  }

  public Mono<JwtResponse> editUserEntity(EditUserDto userDto, String email) {
    return userRepository.findByEmail(email)
        .switchIfEmpty(Mono.error(new UserException("No user found")))
        .flatMap(user -> {
          if (!userDto.username().isBlank() && userDto.username().length() > 2) {
            user.setUsername(userDto.username());
          }
          if (userDto.age() != null && userDto.age().compareTo(0) > 0) {
            user.setAge(userDto.age());
          }
          if (!userDto.gender().isBlank() && Arrays.stream(Gender.values()).anyMatch(value -> value.name().equals(userDto.gender()))) {
            user.setGender(Gender.valueOf(userDto.gender()));
          }
          if (userDto.height() != null && userDto.height().compareTo(BigDecimal.ZERO) > 0) {
            user.setHeight(userDto.height());
          }
          if (userDto.kilograms() != null && userDto.kilograms().compareTo(BigDecimal.ZERO) > 0) {
            user.setKilograms(userDto.kilograms());
          }
          if (!userDto.workoutState().isBlank() && Arrays.stream(WorkoutState.values()).anyMatch(value -> value.name().equals(userDto.workoutState()))) {
            user.setWorkoutState(WorkoutState.valueOf(userDto.workoutState()));
          }
          return fillUserWithCompleteDetails(user);
        })
        .flatMap(jwtService::authAndGenerateJwtToken);
  }

  public Mono<Void> addNewRecordByUserId(String userEmail) {
    return userRepository
        .findByEmail(userEmail)
        .switchIfEmpty(Mono.error(new UserException("No user found")))
        .flatMap(recordService::createRecord);
  }

  private Mono<UserEntity> fillUserWithCompleteDetails(UserEntity entity) {
    if (entity.getKilograms() != null &&
        entity.getWorkoutState() != null &&
        entity.getGender() != null &&
        entity.getHeight() != null &&
        entity.getAge() != null &&
        entity.getUserDetails().equals(UserDetails.NOT_COMPLETED)) {
      entity.setUserDetails(UserDetails.COMPLETED);
      return recordService
          .createRecord(entity)
          .then(userRepository.save(entity));
    }
    return userRepository
        .save(entity);
  }
}
