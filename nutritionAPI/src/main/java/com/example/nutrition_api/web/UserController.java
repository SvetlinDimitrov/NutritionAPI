package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.users.dto.UserCreateRequest;
import com.example.nutrition_api.domain.users.dto.UserUpdateRequest;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.service.UserService;
import com.example.nutrition_api.infrastructure.open_ai.UserControllerDocs;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@Valid @RequestBody UserCreateRequest userDto) {
    userService.create(userDto);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("isAuthenticated()")
  public UserView get() {
    return userService.getLoggedInUser();
  }

  @PatchMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  @PreAuthorize("isAuthenticated()")
  public UserView edit(@RequestBody UserUpdateRequest userDto) {
    return userService.edit(userDto);
  }
}
