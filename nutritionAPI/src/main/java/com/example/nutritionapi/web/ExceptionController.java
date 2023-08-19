package com.example.nutritionapi.web;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.nutritionapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController{

    @ExceptionHandler(ElectrolyteNotFound.class)
    public ResponseEntity<String> catchElectrolyteNotFound(ElectrolyteNotFound e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> catchMacroNotFoundError(MacronutrientNotFound e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RecordNotFound.class)
    public ResponseEntity<String> catchRecordNotFoundException(RecordNotFound e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectNutrientChange.class)
    public ResponseEntity<String> catchIncorrectNutrientChangeException(IncorrectNutrientChange e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongUserCredentials.class)
    public ResponseEntity<String> wrongCredentialsErrorCaught(WrongUserCredentials e){
        return new ResponseEntity<>(e.getMessageWithErrors() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VitaminNotFound.class)
    public ResponseEntity<String> notFoundVitamin(VitaminNotFound exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    //TODO:Not Working
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex, WebRequest request) {

        String message = "Token expired. Please refresh your token or log in again.";

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("timestamp", new Date());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
