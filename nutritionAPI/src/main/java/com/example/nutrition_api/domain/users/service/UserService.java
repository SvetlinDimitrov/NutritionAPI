package com.example.nutrition_api.domain.users.service;

import com.example.nutrition_api.domain.users.dto.UserCreateRequest;
import com.example.nutrition_api.domain.users.dto.UserUpdateRequest;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.User;

public interface UserService {

  boolean existsByEmail(String email);

  void create(UserCreateRequest userDto);

  User findByEmail(String email);

  UserView edit(UserUpdateRequest userDto);

  void save(User user);

  UserView getLoggedInUser();
}
