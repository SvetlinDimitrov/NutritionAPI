package com.example.nutrition_api.infrastructure.exceptions.throwable;


import com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages;

public class ForbiddenException extends BaseException {

  public ForbiddenException(ExceptionMessages exceptionMessage) {
    super(exceptionMessage);
  }
}