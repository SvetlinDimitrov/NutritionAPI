package com.example.nutrition_api.domain.users.annotations;

import com.example.nutrition_api.domain.users.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final UserService userService;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    return userService.notUsedEmail(email);
  }
}