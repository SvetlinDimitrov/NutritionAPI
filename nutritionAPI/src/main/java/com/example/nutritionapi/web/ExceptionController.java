package com.example.nutritionapi.web;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.nutritionapi.exceptions.*;
import com.example.nutritionapi.service.ElectrolyteServiceImp;
import com.example.nutritionapi.service.MacronutrientServiceImp;
import com.example.nutritionapi.service.VitaminServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    private final ElectrolyteServiceImp electrolyteServiceImp;
    private final MacronutrientServiceImp macronutrientServiceImp;
    private final VitaminServiceImp vitaminServiceImp;

    public ExceptionController(ElectrolyteServiceImp electrolyteServiceImp, MacronutrientServiceImp macronutrientServiceImp, VitaminServiceImp vitaminServiceImp) {
        this.electrolyteServiceImp = electrolyteServiceImp;
        this.macronutrientServiceImp = macronutrientServiceImp;
        this.vitaminServiceImp = vitaminServiceImp;
    }

    @ExceptionHandler(ElectrolyteNotFoundException.class)
    public ResponseEntity<String> catchElectrolyteNotFound(ElectrolyteNotFoundException e) {

        String message =
                "Electrolyte with name '" + e.getMessage() + "' does not existed in the data.\n" +
                        "The available search names are: " + electrolyteServiceImp.getAllElectrolytesNames();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MacronutrientNotFoundException.class)
    public ResponseEntity<String> catchMacroNotFoundError(MacronutrientNotFoundException e) {

        String message =
                "Electrolyte with name '" + e.getMessage() + "' does not existed in the data.\n" +
                        "The available search names are: " + macronutrientServiceImp.getAllMacrosNames();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> catchRecordNotFoundException(RecordNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectNutrientChangeException.class)
    public ResponseEntity<String> catchIncorrectNutrientChangeException(IncorrectNutrientChangeException e) {

        String message = "This does not match any nutrient in the dalyRecord/dataBase" +
                System.lineSeparator() +
                "All available macros names: " +
                macronutrientServiceImp.getAllMacrosNames() +
                System.lineSeparator() +
                "All available vitamins names: " +
                vitaminServiceImp.getAllVitaminsNames() +
                System.lineSeparator() +
                "All available electrolytes names: " +
                electrolyteServiceImp.getAllElectrolytesNames();


        return !e.getMessage().isBlank() ? new ResponseEntity<>(message, HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(WrongUserCredentialsException.class)
    public ResponseEntity<String> wrongCredentialsErrorCaught(WrongUserCredentialsException e) {
        return new ResponseEntity<>(e.getMessageWithErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VitaminNotFoundException.class)
    public ResponseEntity<String> notFoundVitamin(VitaminNotFoundException exception) {

        String message =
                "Vitamin with name " + exception.getMessage() + " does not existed in the data.\n" +
                        "The available search names are: " + vitaminServiceImp.getAllVitaminsNames();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    //TODO:Not Working
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException() {

        String message = "Token expired. Please refresh your token or log in again.";

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("timestamp", new Date());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
