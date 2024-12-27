package com.example.nutrition_api.infrastructure.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;

public class IncorrectNutrientChangeException extends Exception{
    private String messageWithErrors;
    public IncorrectNutrientChangeException(List<FieldError> errors) {
        super();
        this.messageWithErrors = errors.stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.joining("\n"));
    }

    public IncorrectNutrientChangeException(String message) {
        super(message);
    }
    public String getMessageWithErrors() {
        return messageWithErrors;
    }
}
