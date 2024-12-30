package com.example.nutrition_api.domain.users.service;

import com.example.nutrition_api.domain.record.service.RecordService;
import com.example.nutrition_api.domain.users.dto.UserCreateRequest;
import com.example.nutrition_api.domain.users.dto.UserUpdateRequest;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.enums.UserDetails;
import com.example.nutrition_api.domain.users.repository.UserRepository;
import com.example.nutrition_api.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;
  private final RecordService recordService;
  private final UserMapper mapper;

  public boolean uniqueEmail(String email) {
    return userRepository.findByEmail(email).isEmpty();
  }

  public void create(UserCreateRequest userDto) {
    User toSave = fillUserWithCompleteDetails(mapper.toEntity(userDto));

    userRepository.save(toSave);
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no user found with the given email"));
  }

  public UserView findByEmailView(String email) {
    return mapper.toView(findByEmail(email));
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("no user found with the given id"));
  }

  public UserView getById(Long userId) {
    return mapper.toView(findById(userId));
  }

  public void edit(UserUpdateRequest userDto, Long userId) {
    User userToUpdate = fillUserWithCompleteDetails(mapper.updateEntity(findById(userId), userDto));

    userRepository.save(userToUpdate);
  }

  public void save(User user) {
    userRepository.save(user);
  }

  private User fillUserWithCompleteDetails(User entity) {
    if (entity.getKilograms() != null && entity.getWorkoutState() != null && entity.getGender() != null && entity.getHeight() != null && entity.getAge() != null && (entity.getRecords() == null || entity.getRecords()
        .isEmpty())) {
      entity.setUserDetails(UserDetails.COMPLETED);
      entity.getRecords().add(recordService.create(entity));
    }
    return entity;
  }
}
