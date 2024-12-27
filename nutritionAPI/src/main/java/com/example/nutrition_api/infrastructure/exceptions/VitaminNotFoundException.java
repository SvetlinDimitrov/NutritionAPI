package com.example.nutrition_api.infrastructure.exceptions;

public class VitaminNotFoundException extends Exception{
    public VitaminNotFoundException(String message) {
        super(message);
    }
}
