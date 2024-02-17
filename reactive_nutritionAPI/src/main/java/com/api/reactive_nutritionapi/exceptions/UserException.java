package com.api.reactive_nutritionapi.exceptions;

import lombok.Getter;

@Getter
public class UserException extends Exception {
  private final String message;

  public UserException(String message) {
    super(message);
    this.message = message;
  }
}
