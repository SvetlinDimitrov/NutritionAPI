package com.example.nutritionapi.service;

import com.example.nutritionapi.repos.ElectrolyteRepository;
import com.example.nutritionapi.repos.MacronutrientRepository;
import com.example.nutritionapi.repos.VitaminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InitService {
    private final ElectrolyteRepository electrolyteRepository;
    private final VitaminRepository vitaminRepository;
    private final MacronutrientRepository macronutrientRepository;

    public InitService(ElectrolyteRepository electrolyteRepository, VitaminRepository vitaminRepository, MacronutrientRepository macronutrientRepository) {
        this.electrolyteRepository = electrolyteRepository;
        this.vitaminRepository = vitaminRepository;
        this.macronutrientRepository = macronutrientRepository;
    }

    @PostConstruct
    public void initData(){
        if(electrolyteRepository.count() == 0){

        }
        if(vitaminRepository.count() == 0){

        }
        if(macronutrientRepository.count() == 0){

        }
    }
}
