package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.domain.macros.service.MacronutrientServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.MacronutrientNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
