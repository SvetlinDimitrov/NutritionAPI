package com.api.reactive_nutritionapi.web;

import com.api.reactive_nutritionapi.domain.dtos.viewDtos.MacronutrientView;
import com.api.reactive_nutritionapi.exceptions.MacronutrientNotFoundException;
import com.api.reactive_nutritionapi.service.MacronutrientServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<MacronutrientView> getAllMacrosView() {
        return macronutrientService.getAllMacrosView();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MacronutrientView> getMacroViewByName(@PathVariable String name) throws MacronutrientNotFoundException {
        return macronutrientService.getMacroViewByName(name);
    }

}
