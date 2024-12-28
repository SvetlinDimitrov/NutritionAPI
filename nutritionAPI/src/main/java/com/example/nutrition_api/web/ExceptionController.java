package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.electrolyte.service.ElectrolyteService;
import com.example.nutrition_api.domain.macros.service.MacronutrientService;
import com.example.nutrition_api.domain.vitamin.service.VitaminService;
import com.example.nutrition_api.infrastructure.exceptions.ElectrolyteNotFoundException;
import com.example.nutrition_api.infrastructure.exceptions.IncorrectNutrientChangeException;
import com.example.nutrition_api.infrastructure.exceptions.MacronutrientNotFoundException;
import com.example.nutrition_api.infrastructure.exceptions.RecordNotFoundException;
import com.example.nutrition_api.infrastructure.exceptions.VitaminNotFoundException;
import com.example.nutrition_api.infrastructure.exceptions.WrongUserCredentialsException;
import com.example.nutrition_api.infrastructure.exceptions.dto.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class ExceptionController {

  private final ElectrolyteService electrolyteService;
  private final MacronutrientService macronutrientService;
  private final VitaminService vitaminService;

  @ExceptionHandler(ElectrolyteNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse catchElectrolyteNotFound(ElectrolyteNotFoundException e) {

    return new ExceptionResponse(
        "Electrolyte with name '" + e.getMessage() + "' does not existed in the data.\n" +
            "The available search names are: "
            + electrolyteService.getAllElectrolytesNames());

  }

  @ExceptionHandler(MacronutrientNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse catchMacroNotFoundError(MacronutrientNotFoundException e) {

    return new ExceptionResponse(
        "Electrolyte with name '" + e.getMessage() + "' does not existed in the data.\n" +
            "The available search names are: " + macronutrientService.getAllMacrosNames());

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
        macronutrientService.getAllMacrosNames() +
        System.lineSeparator() +
        "All available vitamins names: " +
        vitaminService.getAllVitaminsNames() +
        System.lineSeparator() +
        "All available electrolytes names: " +
        electrolyteService.getAllElectrolytesNames();

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
            "The available search names are: " + vitaminService.getAllVitaminsNames();

    return new ExceptionResponse(message);
  }
}
