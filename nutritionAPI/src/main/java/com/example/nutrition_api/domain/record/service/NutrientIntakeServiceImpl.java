package com.example.nutrition_api.domain.record.service;

import com.example.nutrition_api.domain.electrolyte.service.ElectrolyteService;
import com.example.nutrition_api.domain.macros.service.MacronutrientService;
import com.example.nutrition_api.domain.record.entity.NutritionIntake;
import com.example.nutrition_api.domain.record.entity.Record;
import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.enums.WorkoutState;
import com.example.nutrition_api.domain.vitamin.service.VitaminService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NutrientIntakeServiceImpl implements NutrientIntakeService {

  private final VitaminService vitaminService;
  private final MacronutrientService macroService;
  private final ElectrolyteService electrolyteService;

  public List<NutritionIntake> create(Gender gender, BigDecimal caloriesPerDay, WorkoutState state, Record record) {
    List<NutritionIntake> nutritionIntakeEntities = fillDailyIntake(gender, record);
    macroService.findAll().forEach(macro -> {
      NutritionIntake intake = new NutritionIntake();
      intake.setNutrientName(macro.getName());
      intake.setMeasurement("grams (g)");
      intake.setNutrientType("Macronutrient");
      intake.setLowerBoundIntake(inactiveState(state) ? caloriesPerDay.multiply(BigDecimal.valueOf(macro.getInactiveState())) : caloriesPerDay.multiply(BigDecimal.valueOf(macro.getActiveState())));
      intake.setUpperBoundIntake(inactiveState(state) ? caloriesPerDay.multiply(BigDecimal.valueOf(macro.getInactiveState())) : caloriesPerDay.multiply(BigDecimal.valueOf(macro.getActiveState())));
      intake.setRecord(record);

      if (intake.getNutrientName().equals("Fat")) {
        intake.setUpperBoundIntake(intake.getUpperBoundIntake().divide(new BigDecimal("9"), RoundingMode.HALF_UP));
        intake.setLowerBoundIntake(intake.getLowerBoundIntake().divide(new BigDecimal("9"), RoundingMode.HALF_UP));
      } else {
        intake.setUpperBoundIntake(intake.getUpperBoundIntake().divide(new BigDecimal("4"), RoundingMode.HALF_UP));
        intake.setLowerBoundIntake(intake.getLowerBoundIntake().divide(new BigDecimal("4"), RoundingMode.HALF_UP));
      }

      nutritionIntakeEntities.add(intake);
    });
    return nutritionIntakeEntities;
  }

  private List<NutritionIntake> fillDailyIntake(Gender gender, Record record) {
    List<NutritionIntake> nutritionIntakeEntities = new ArrayList<>();

    vitaminService.findAll().forEach(vitamin -> {
      BigDecimal lowerBoundIntake = gender.equals(Gender.MALE) ? vitamin.getMaleLowerBoundIntake() : vitamin.getFemaleLowerBoundIntake();
      BigDecimal upperBoundIntake = gender.equals(Gender.MALE) ? vitamin.getMaleHigherBoundIntake() : vitamin.getFemaleHigherBoundIntake();
      nutritionIntakeEntities.add(createNutritionIntake(vitamin.getName(), "Vitamin", vitamin.getMeasure(), lowerBoundIntake, upperBoundIntake, record));
    });

    electrolyteService.findAll().forEach(electrolyte -> {
      BigDecimal lowerBoundIntake = gender.equals(Gender.MALE) ? electrolyte.getMaleLowerBoundIntake() : electrolyte.getFemaleLowerBoundIntake();
      BigDecimal upperBoundIntake = gender.equals(Gender.MALE) ? electrolyte.getMaleHigherBoundIntake() : electrolyte.getFemaleHigherBoundIntake();
      nutritionIntakeEntities.add(createNutritionIntake(electrolyte.getName(), "Electrolyte", electrolyte.getMeasure(), lowerBoundIntake, upperBoundIntake, record));
    });

    return nutritionIntakeEntities;
  }

  private NutritionIntake createNutritionIntake(String nutrientName, String nutrientType, String measurement, BigDecimal lowerBoundIntake, BigDecimal upperBoundIntake, Record record) {
    NutritionIntake nutritionIntake = new NutritionIntake();
    nutritionIntake.setNutrientName(nutrientName);
    nutritionIntake.setNutrientType(nutrientType);
    nutritionIntake.setMeasurement(measurement);
    nutritionIntake.setLowerBoundIntake(lowerBoundIntake);
    nutritionIntake.setUpperBoundIntake(upperBoundIntake);
    nutritionIntake.setRecord(record);
    return nutritionIntake;
  }

  private Boolean inactiveState(WorkoutState state) {
    return state.equals(WorkoutState.SEDENTARY) || state.equals(WorkoutState.LIGHTLY_ACTIVE);
  }
}
