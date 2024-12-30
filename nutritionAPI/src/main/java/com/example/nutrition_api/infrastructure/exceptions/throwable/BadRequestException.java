package com.example.nutrition_api.infrastructure.exceptions.throwable;


import com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages;

public class BadRequestException extends BaseException {

  public BadRequestException(ExceptionMessages exceptionMessage) {
    super(exceptionMessage);
  }

  public BadRequestException(ExceptionMessages exceptionMessage, Object... values) {
    super(exceptionMessage, values);
  }
}
