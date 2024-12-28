package com.example.nutrition_api.domain.users.service;

import com.example.nutrition_api.domain.users.dto.UserUpdateRequest;
import com.example.nutrition_api.domain.users.dto.UserCreateRequest;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.User;

public interface UserService {

  boolean notUsedEmail(String email);

  void create(UserCreateRequest userDto);

  User findByEmail(String email);

  User findById(Long id);

  UserView findByEmailView(String email);

  UserView getById(Long userId);

  void edit(UserUpdateRequest userDto, Long userId);
}
