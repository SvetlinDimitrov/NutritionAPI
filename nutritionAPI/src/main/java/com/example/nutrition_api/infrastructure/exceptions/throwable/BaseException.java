package com.example.nutrition_api.infrastructure.exceptions.throwable;

import com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages;
import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

  private final ExceptionMessages exceptionMessage;

  protected BaseException(ExceptionMessages exceptionMessage, Object... values) {
    super(String.format(exceptionMessage.message, values));
    this.exceptionMessage = exceptionMessage;
  }

  protected BaseException(ExceptionMessages exceptionMessage) {
    super(exceptionMessage.message);
    this.exceptionMessage = exceptionMessage;
  }

  public String getTitle() {
    return exceptionMessage.title;
  }
}