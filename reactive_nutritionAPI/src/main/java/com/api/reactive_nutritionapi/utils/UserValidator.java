package com.api.reactive_nutritionapi.utils;

import com.api.reactive_nutritionapi.domain.dtos.user.RegisterUserDto;
import com.api.reactive_nutritionapi.exceptions.UserException;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

public class UserValidator {

  public static Mono<RegisterUserDto> validateUsername(Mono<RegisterUserDto> user) {
    return user.flatMap(dto -> {
      if (dto.username().isBlank() || dto.username().trim().length() < 2) {
        return Mono.error(new UserException("Username must be greater than 2 symbols"));
      } else {
        return Mono.just(dto);
      }
    });
  }

  public static Mono<RegisterUserDto> validateEmail(Mono<RegisterUserDto> user) {
    return user.flatMap(dto -> {
      String email = dto.email();
      if (email.isBlank() || !Pattern.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$", email)) {
        return Mono.error(new UserException("Email is invalid or empty"));
      } else {
        return Mono.just(dto);
      }
    });
  }

  public static Mono<RegisterUserDto> validatePassword(Mono<RegisterUserDto> user) {
    return user.flatMap(dto -> {
      if (dto.password().isBlank() || dto.password().trim().length() < 4) {
        return Mono.error(new UserException("Password cannot be empty or less than 4 symbols long"));
      } else {
        return Mono.just(dto);
      }
    });
  }
}
