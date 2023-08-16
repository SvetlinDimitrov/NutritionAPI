package com.example.nutritionapi.service;

import com.example.nutritionapi.repos.MacronutrientRepository;
import org.springframework.stereotype.Service;

@Service
public class MacronutrientServiceImp {
    private final MacronutrientRepository macronutrientRepository;

    public MacronutrientServiceImp(MacronutrientRepository macronutrientRepository) {
        this.macronutrientRepository = macronutrientRepository;
    }
}
