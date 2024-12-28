package com.example.nutrition_api.domain.users.service;

import com.example.nutrition_api.domain.record.service.RecordService;
import com.example.nutrition_api.domain.users.dto.UserCreateRequest;
import com.example.nutrition_api.domain.users.dto.UserUpdateRequest;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.enums.UserDetails;
import com.example.nutrition_api.domain.users.repository.UserRepository;
import com.example.nutrition_api.infrastructure.mappers.ViewConverter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;
  private final RecordService recordService;
  private final PasswordEncoder passwordEncoder;
  private final ViewConverter converter;


  public UserServiceImp(UserRepository userRepository, RecordService recordService, PasswordEncoder passwordEncoder, ViewConverter converter) {
    this.userRepository = userRepository;
    this.recordService = recordService;
    this.passwordEncoder = passwordEncoder;
    this.converter = converter;
  }

  public boolean notUsedEmail(String email) {
    return userRepository.findByEmail(email).isEmpty();
  }

  public void create(UserCreateRequest userDto) {
    User entity = toUserEntity(userDto);

    fillUserWithCompleteDetails(entity);
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no user found with the given email"));
  }

  public UserView findByEmailView(String email) {
    return converter.toView(findByEmail(email));
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("no user found with the given id"));
  }

  public UserView getById(Long userId) {
    return converter.toView(findById(userId));
  }

  public void edit(UserUpdateRequest userDto, Long userId) {
    User user = findById(userId);

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

  private void fillUserWithCompleteDetails(User entity) {
    if (entity.getKilograms() != null && entity.getWorkoutState() != null && entity.getGender() != null && entity.getHeight() != null && entity.getAge() != null && (entity.getRecords() == null || entity.getRecords().isEmpty())) {
      entity.setUserDetails(UserDetails.COMPLETED);
      entity.getRecords().add(recordService.create(entity));
    }
    userRepository.save(entity);
  }

  private User toUserEntity(UserCreateRequest userDto) {
    return new User().setEmail(userDto.email()).setPassword(passwordEncoder.encode(userDto.password())).setUsername(userDto.username()).setWorkoutState(userDto.workoutState()).setKilograms(userDto.kg()).setGender(userDto.gender()).setHeight(userDto.height()).setAge(userDto.age());
  }
}
