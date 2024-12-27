package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.electrolyte.service.ElectrolyteServiceImp;
import com.example.nutrition_api.domain.macros.service.MacronutrientServiceImp;
import com.example.nutrition_api.domain.vitamin.service.VitaminServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.ElectrolyteNotFoundException;
import com.example.nutrition_api.infrastructure.exceptions.IncorrectNutrientChangeException;
import com.example.nutrition_api.infrastructure.exceptions.MacronutrientNotFoundException;
import com.example.nutrition_api.infrastructure.exceptions.RecordNotFoundException;
import com.example.nutrition_api.infrastructure.exceptions.VitaminNotFoundException;
import com.example.nutrition_api.infrastructure.exceptions.WrongUserCredentialsException;
import com.example.nutrition_api.infrastructure.exceptions.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse catchElectrolyteNotFound(ElectrolyteNotFoundException e) {

        return new ExceptionResponse(
                "Electrolyte with name '" + e.getMessage() + "' does not existed in the data.\n" +
                        "The available search names are: " + electrolyteServiceImp.getAllElectrolytesNames());

    }

    @ExceptionHandler(MacronutrientNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse catchMacroNotFoundError(MacronutrientNotFoundException e) {

        return new ExceptionResponse(
                "Electrolyte with name '" + e.getMessage() + "' does not existed in the data.\n" +
                        "The available search names are: " + macronutrientServiceImp.getAllMacrosNames());

    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse catchRecordNotFoundException(RecordNotFoundException e) {
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(IncorrectNutrientChangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse catchIncorrectNutrientChangeException(
        IncorrectNutrientChangeException e) {

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

        return new ExceptionResponse(!e.getMessage().isBlank() ? message : e.getMessage());
    }

    @ExceptionHandler(WrongUserCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse wrongCredentialsErrorCaught(WrongUserCredentialsException e) {
        return new ExceptionResponse(e.getMessageWithErrors());
    }

    @ExceptionHandler(VitaminNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse notFoundVitamin(VitaminNotFoundException exception) {

        String message =
                "Vitamin with name " + exception.getMessage() + " does not existed in the data.\n" +
                        "The available search names are: " + vitaminServiceImp.getAllVitaminsNames();

        return new ExceptionResponse(message);
    }
}
