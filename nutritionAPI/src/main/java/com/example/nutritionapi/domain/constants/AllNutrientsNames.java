package com.example.nutritionapi.domain.constants;

import com.example.nutritionapi.service.ElectrolyteServiceImp;
import com.example.nutritionapi.service.MacronutrientServiceImp;
import com.example.nutritionapi.service.VitaminServiceImp;
import org.springframework.stereotype.Component;

@Component
public class AllNutrientsNames {

    private static  VitaminServiceImp vitaminServiceImp;
    private static MacronutrientServiceImp macronutrientServiceImp;
    private static  ElectrolyteServiceImp electrolyteServiceImp;

    public AllNutrientsNames(VitaminServiceImp vitaminServiceImp, MacronutrientServiceImp macronutrientServiceImp, ElectrolyteServiceImp electrolyteServiceImp) {
        AllNutrientsNames.vitaminServiceImp = vitaminServiceImp;
        AllNutrientsNames.macronutrientServiceImp = macronutrientServiceImp;
        AllNutrientsNames.electrolyteServiceImp = electrolyteServiceImp;
    }

    public static String getAllNutritionNames(){
        String allVitaminsNames = "All Vitamin names: "+vitaminServiceImp.getAllVitaminsNames();
        String allMacrosNames = "All Macronutrient names: "+macronutrientServiceImp.getAllMacrosNames();
        String allElectrolytesNames = "All Electrolyte names: "+electrolyteServiceImp.getAllElectrolytesNames();
        return String.format("%s%n%s%n%s%n",allVitaminsNames , allMacrosNames , allElectrolytesNames);
    }
}
