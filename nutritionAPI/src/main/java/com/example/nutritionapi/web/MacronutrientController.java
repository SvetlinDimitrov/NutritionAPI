package com.example.nutritionapi.web;

import com.example.nutritionapi.domain.dtos.viewDtos.MacronutrientView;
import com.example.nutritionapi.exceptions.MacronutrientNotFoundException;
import com.example.nutritionapi.service.MacronutrientServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<MacronutrientView> getAllMacrosView() {
        return macronutrientService.getAllMacrosView();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public MacronutrientView getMacroViewByName(@PathVariable String name) throws MacronutrientNotFoundException {
        return macronutrientService.getMacroViewByName(name);
    }

}
