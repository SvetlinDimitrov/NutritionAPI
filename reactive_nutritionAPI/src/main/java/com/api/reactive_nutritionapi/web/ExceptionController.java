package com.api.reactive_nutritionapi.web;

import com.api.reactive_nutritionapi.domain.dtos.viewDtos.ExceptionResponse;
import com.api.reactive_nutritionapi.exceptions.*;
import com.api.reactive_nutritionapi.service.ElectrolyteServiceImp;
import com.api.reactive_nutritionapi.service.MacronutrientServiceImp;
import com.api.reactive_nutritionapi.service.VitaminServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
  public Mono<ExceptionResponse> catchElectrolyteNotFound(ElectrolyteNotFoundException e) {

    return electrolyteServiceImp.getAllElectrolytesNames()
        .map(names -> new ExceptionResponse(
            "Electrolyte with name '" + e.getMessage() + "' does not exist in the data.\n" +
                "The available search names are: " + names));

  }

  @ExceptionHandler(MacronutrientNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<ExceptionResponse> catchMacroNotFoundError(MacronutrientNotFoundException e) {

    return macronutrientServiceImp.getAllMacrosNames()
        .map(names -> new ExceptionResponse(
            "Electrolyte with name '" + e.getMessage() + "' does not existed in the data.\n" +
                "The available search names are: " + names));

  }

  @ExceptionHandler(RecordNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<ExceptionResponse> catchRecordNotFoundException(RecordNotFoundException e) {
    return Mono.just(new ExceptionResponse(e.getMessage()));
  }

  @ExceptionHandler(UserException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Mono<ExceptionResponse> catchUserNotFound(UserException e) {
    return Mono.just(new ExceptionResponse(e.getMessage()));
  }

  @ExceptionHandler(IncorrectNutrientChangeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<ExceptionResponse> catchIncorrectNutrientChangeException(IncorrectNutrientChangeException e) {
    return Mono.zip(macronutrientServiceImp.getAllMacrosNames(), vitaminServiceImp.getAllVitaminsNames(), electrolyteServiceImp.getAllElectrolytesNames())
        .map((tuple) ->
            new ExceptionResponse(
                "This does not match any nutrient in the dalyRecord/dataBase" +
                    System.lineSeparator() +
                    "All available macros names: " +
                    tuple.getT1() +
                    System.lineSeparator() +
                    "All available vitamins names: " +
                    tuple.getT2() +
                    System.lineSeparator() +
                    "All available electrolytes names: " +
                    tuple.getT3()
            )
        );

  }

  @ExceptionHandler(WrongUserCredentialsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<ExceptionResponse> wrongCredentialsErrorCaught(WrongUserCredentialsException e) {
    return Mono.just(new ExceptionResponse(e.getMessageWithErrors()));
  }

  @ExceptionHandler(VitaminNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<ExceptionResponse> notFoundVitamin(VitaminNotFoundException exception) {

    return vitaminServiceImp.getAllVitaminsNames()
        .map(message -> new ExceptionResponse(
            "Vitamin with name " + exception.getMessage() + " does not existed in the data.\n" +
                "The available search names are: " + message
        ));
  }
}
