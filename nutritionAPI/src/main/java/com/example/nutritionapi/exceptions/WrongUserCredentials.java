package com.example.nutritionapi.exceptions;

import org.springframework.validation.FieldError;
import java.util.List;
import java.util.stream.Collectors;

public class WrongUserCredentials extends Exception{

    private final String messageWithErrors;

    public WrongUserCredentials(List<FieldError> errors) {
        super();

        this.messageWithErrors = errors.stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.joining("\n"));

    }

    public String getMessageWithErrors() {
        return messageWithErrors;
    }
}
