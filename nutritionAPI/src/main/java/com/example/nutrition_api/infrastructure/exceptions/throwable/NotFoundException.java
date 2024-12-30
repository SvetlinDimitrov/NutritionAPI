package com.example.nutrition_api.infrastructure.exceptions.throwable;


import com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages;

public class NotFoundException extends BaseException {

  public NotFoundException(ExceptionMessages exceptionMessage, Object... values) {
    super(exceptionMessage, values);
  }
}
