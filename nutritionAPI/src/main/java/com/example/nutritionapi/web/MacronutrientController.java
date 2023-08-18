package com.example.nutritionapi.web;

import com.example.nutritionapi.domain.dtos.viewDtos.MacronutrientView;
import com.example.nutritionapi.exceptions.MacronutrientNotFound;
import com.example.nutritionapi.service.MacronutrientServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutritionApi/macronutrient")
public class MacronutrientController {

    private final MacronutrientServiceImp macronutrientService;

    public MacronutrientController(MacronutrientServiceImp macronutrientService) {
        this.macronutrientService = macronutrientService;
    }

    @GetMapping
    public ResponseEntity<List<MacronutrientView>> getAllMacrosView(){
        return new ResponseEntity<>(macronutrientService.getAllMacrosView() , HttpStatus.FOUND);
    }

    @GetMapping("/{name}")
    public ResponseEntity<MacronutrientView> getMacroViewByName(@PathVariable String name) throws MacronutrientNotFound {
        return new ResponseEntity<>(macronutrientService.getMacroViewByName(name) , HttpStatus.FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> catchMacroNotFoundError(MacronutrientNotFound e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }
}