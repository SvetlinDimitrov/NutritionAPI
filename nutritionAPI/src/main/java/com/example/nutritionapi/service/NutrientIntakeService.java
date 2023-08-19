package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;
import com.example.nutritionapi.domain.entity.NutritionIntakeEntity;
import com.example.nutritionapi.domain.entity.RecordEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class NutrientIntakeService {
    private final VitaminServiceImp vitaminService;
    private final MacronutrientServiceImp macroService;
    private final ElectrolyteServiceImp electrolyteService;

    public NutrientIntakeService(VitaminServiceImp vitaminService, MacronutrientServiceImp macroService, ElectrolyteServiceImp electrolyteService) {
        this.vitaminService = vitaminService;
        this.macroService = macroService;
        this.electrolyteService = electrolyteService;
    }

    public List<NutritionIntakeEntity> create(Gender gender, BigDecimal caloriesPerDay, WorkoutState state , RecordEntity record) {
        List<NutritionIntakeEntity> nutritionIntakeEntities = fillDailyIntake(gender ,record);
        macroService.findAll()
                .forEach(macro -> {
                    NutritionIntakeEntity intake = new NutritionIntakeEntity();
                    intake.setNutrientName(macro.getName());
                    intake.setMeasurement("grams (g)");
                    intake.setNutrientType("Macronutrient");
                    intake.setLowerBoundIntake(inactiveState(state) ?
                            caloriesPerDay.multiply(BigDecimal.valueOf(macro.getInactiveState()))
                            : caloriesPerDay.multiply(BigDecimal.valueOf(macro.getActiveState())));
                    intake.setUpperBoundIntake(inactiveState(state) ?
                            caloriesPerDay.multiply(BigDecimal.valueOf(macro.getInactiveState()))
                            : caloriesPerDay.multiply(BigDecimal.valueOf(macro.getActiveState())));
                    intake.setRecord(record);

                    if(intake.getNutrientName().equals("Fat")){
                        intake.setUpperBoundIntake(intake.getUpperBoundIntake().divide(new BigDecimal("9"), RoundingMode.HALF_UP));
                        intake.setLowerBoundIntake(intake.getLowerBoundIntake().divide(new BigDecimal("9"), RoundingMode.HALF_UP));
                    }else{
                        intake.setUpperBoundIntake(intake.getUpperBoundIntake().divide(new BigDecimal("4"), RoundingMode.HALF_UP));
                        intake.setLowerBoundIntake(intake.getLowerBoundIntake().divide(new BigDecimal("4"), RoundingMode.HALF_UP));
                    }


                    nutritionIntakeEntities.add(intake);
                });
        return nutritionIntakeEntities;
    }

    private List<NutritionIntakeEntity> fillDailyIntake(Gender gender , RecordEntity record) {
        List<NutritionIntakeEntity> nutritionIntakeEntities = new ArrayList<>();

        vitaminService.findAll().forEach(vitamin -> {
            BigDecimal lowerBoundIntake = gender.equals(Gender.MALE) ? vitamin.getMaleLowerBoundIntake() : vitamin.getFemaleLowerBoundIntake();
            BigDecimal upperBoundIntake = gender.equals(Gender.MALE) ? vitamin.getMaleHigherBoundIntake() : vitamin.getFemaleHigherBoundIntake();
            nutritionIntakeEntities.add(createNutritionIntake(vitamin.getName(), "Vitamin",
                    vitamin.getMeasure(), lowerBoundIntake, upperBoundIntake , record));
        });

        electrolyteService.findAll().forEach(electrolyte -> {
            BigDecimal lowerBoundIntake = gender.equals(Gender.MALE) ? electrolyte.getMaleLowerBoundIntake() : electrolyte.getFemaleLowerBoundIntake();
            BigDecimal upperBoundIntake = gender.equals(Gender.MALE) ? electrolyte.getMaleHigherBoundIntake() : electrolyte.getFemaleHigherBoundIntake();
            nutritionIntakeEntities.add(createNutritionIntake(electrolyte.getName(), "Electrolyte",
                    electrolyte.getMeasure(), lowerBoundIntake, upperBoundIntake ,record));
        });

        return nutritionIntakeEntities;
    }

    private NutritionIntakeEntity createNutritionIntake(String nutrientName, String nutrientType, String measurement,
                                                        BigDecimal lowerBoundIntake, BigDecimal upperBoundIntake , RecordEntity record) {
        return new NutritionIntakeEntity()
                .setNutrientName(nutrientName)
                .setNutrientType(nutrientType)
                .setMeasurement(measurement)
                .setLowerBoundIntake(lowerBoundIntake)
                .setUpperBoundIntake(upperBoundIntake)
                .setRecord(record);
    }

    private Boolean inactiveState(WorkoutState state) {
        return state.equals(WorkoutState.SEDENTARY) || state.equals(WorkoutState.LIGHTLY_ACTIVE);
    }
}
