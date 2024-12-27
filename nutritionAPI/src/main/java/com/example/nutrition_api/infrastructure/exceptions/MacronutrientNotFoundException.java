package com.example.nutrition_api.infrastructure.exceptions;

public class MacronutrientNotFoundException extends Exception{
    public MacronutrientNotFoundException(String message) {
        super(message);
    }
}
