package com.example.nutrition_api.infrastructure.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;

public class WrongUserCredentialsException extends Exception{

    private final String messageWithErrors;

    public WrongUserCredentialsException(List<FieldError> errors) {
        super();

        this.messageWithErrors = errors.stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.joining("\n"));

    }

    public String getMessageWithErrors() {
        return messageWithErrors;
    }
}
