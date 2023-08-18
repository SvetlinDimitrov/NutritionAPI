package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.Gender;
import com.example.nutritionapi.domain.constants.WorkoutState;
import com.example.nutritionapi.domain.entity.NutritionIntake;
import com.example.nutritionapi.domain.entity.RecordEntity;
import com.example.nutritionapi.repos.ElectrolyteRepository;
import com.example.nutritionapi.repos.MacronutrientRepository;
import com.example.nutritionapi.repos.VitaminRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class NutrientIntakeService {
    private final VitaminRepository vitaminRepository;
    private final MacronutrientRepository macronutrientRepository;
    private final ElectrolyteRepository electrolyteRepository;

    public NutrientIntakeService(VitaminRepository vitaminRepository, MacronutrientRepository macronutrientRepository, ElectrolyteRepository electrolyteRepository) {
        this.vitaminRepository = vitaminRepository;
        this.macronutrientRepository = macronutrientRepository;
        this.electrolyteRepository = electrolyteRepository;
    }

    public List<NutritionIntake> create(Gender gender, BigDecimal caloriesPerDay, WorkoutState state , RecordEntity record) {
        List<NutritionIntake> nutritionIntakes = fillDailyIntake(gender ,record);
        macronutrientRepository.findAll()
                .forEach(macro -> {
                    NutritionIntake intake = new NutritionIntake();
                    intake.setNutrientName(macro.getName());
                    intake.setMeasurement("grams (g)");
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

                    nutritionIntakes.add(intake);
                });
        return nutritionIntakes;
    }

    private List<NutritionIntake> fillDailyIntake(Gender gender , RecordEntity record) {
        List<NutritionIntake> nutritionIntakes = new ArrayList<>();

        vitaminRepository.findAll().forEach(vitamin -> {
            String type = vitamin.getClass().getSimpleName();
            BigDecimal lowerBoundIntake = gender.equals(Gender.MALE) ? vitamin.getMaleLowerBoundIntake() : vitamin.getFemaleLowerBoundIntake();
            BigDecimal upperBoundIntake = gender.equals(Gender.MALE) ? vitamin.getMaleHigherBoundIntake() : vitamin.getFemaleHigherBoundIntake();
            nutritionIntakes.add(createNutritionIntake(vitamin.getName(), type,
                    vitamin.getMeasure(), lowerBoundIntake, upperBoundIntake , record));
        });

        electrolyteRepository.findAll().forEach(electrolyte -> {
            String type = electrolyte.getClass().getSimpleName();
            BigDecimal lowerBoundIntake = gender.equals(Gender.MALE) ? electrolyte.getMaleLowerBoundIntake() : electrolyte.getFemaleLowerBoundIntake();
            BigDecimal upperBoundIntake = gender.equals(Gender.MALE) ? electrolyte.getMaleHigherBoundIntake() : electrolyte.getFemaleHigherBoundIntake();
            nutritionIntakes.add(createNutritionIntake(electrolyte.getName(), type,
                    electrolyte.getMeasure(), lowerBoundIntake, upperBoundIntake ,record));
        });

        return nutritionIntakes;
    }

    private NutritionIntake createNutritionIntake(String nutrientName, String nutrientType, String measurement,
                                                  BigDecimal lowerBoundIntake, BigDecimal upperBoundIntake , RecordEntity record) {
        return new NutritionIntake()
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
