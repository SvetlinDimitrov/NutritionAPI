package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.users.dto.UserCreateRequest;
import com.example.nutrition_api.domain.users.dto.UserUpdateRequest;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.service.UserService;
import com.example.nutrition_api.infrastructure.exceptions.WrongUserCredentialsException;
import com.example.nutrition_api.infrastructure.open_ai.UserControllerDocs;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@Valid @RequestBody UserCreateRequest userDto,
      BindingResult result) throws WrongUserCredentialsException {
    if (result.hasErrors()) {
      throw new WrongUserCredentialsException(result.getFieldErrors());
    }
    userService.create(userDto);
  }

  @GetMapping("/details")
  @ResponseStatus(HttpStatus.OK)
  public UserView get(Principal principal) {
    String email = principal.getName();
    return userService.findByEmailView(email);
  }

  @PatchMapping("/details")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public UserView edit(Principal principal, @RequestBody UserUpdateRequest userDto) {
    String email = principal.getName();
    User user = userService.findByEmail(email);
    Long userId = user.getId();

    userService.edit(userDto, userId);

    return userService.getById(userId);
  }

}
