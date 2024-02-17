package com.api.reactive_nutritionapi.service;

import com.api.reactive_nutritionapi.domain.constants.enums.Gender;
import com.api.reactive_nutritionapi.domain.constants.enums.WorkoutState;
import com.api.reactive_nutritionapi.domain.entity.NutritionIntakeEntity;
import com.api.reactive_nutritionapi.domain.entity.RecordEntity;
import com.api.reactive_nutritionapi.repos.NutritionIntakeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class NutrientIntakeService {
  private final VitaminServiceImp vitaminService;
  private final MacronutrientServiceImp macroService;
  private final ElectrolyteServiceImp electrolyteService;
  private final NutritionIntakeRepository repository;

  public NutrientIntakeService(VitaminServiceImp vitaminService, MacronutrientServiceImp macroService, ElectrolyteServiceImp electrolyteService, NutritionIntakeRepository repository) {
    this.vitaminService = vitaminService;
    this.macroService = macroService;
    this.electrolyteService = electrolyteService;
    this.repository = repository;
  }

  public Mono<Void> create(Gender gender, BigDecimal caloriesPerDay, WorkoutState state, RecordEntity record) {
    Flux<NutritionIntakeEntity> nutritionIntakeEntityFlux = fillDailyIntake(gender, record);
    return macroService
        .findAll()
        .flatMap(macro -> {
          NutritionIntakeEntity intake = new NutritionIntakeEntity();
          intake.setNutrientName(macro.getName());
          intake.setMeasurement("grams (g)");
          intake.setNutrientType("Macronutrient");
          intake.setLowerBoundIntake(inactiveState(state) ?
              caloriesPerDay.multiply(BigDecimal.valueOf(macro.getInactiveState())) :
              caloriesPerDay.multiply(BigDecimal.valueOf(macro.getActiveState())));
          intake.setUpperBoundIntake(inactiveState(state) ?
              caloriesPerDay.multiply(BigDecimal.valueOf(macro.getInactiveState())) :
              caloriesPerDay.multiply(BigDecimal.valueOf(macro.getActiveState())));
          intake.setRecordId(record.getId());

          if (intake.getNutrientName().equals("Fat")) {
            intake.setUpperBoundIntake(intake.getUpperBoundIntake().divide(new BigDecimal("9"), RoundingMode.HALF_UP));
            intake.setLowerBoundIntake(intake.getLowerBoundIntake().divide(new BigDecimal("9"), RoundingMode.HALF_UP));
          } else {
            intake.setUpperBoundIntake(intake.getUpperBoundIntake().divide(new BigDecimal("4"), RoundingMode.HALF_UP));
            intake.setLowerBoundIntake(intake.getLowerBoundIntake().divide(new BigDecimal("4"), RoundingMode.HALF_UP));
          }
          return Mono.just(intake);
        })
        .concatWith(nutritionIntakeEntityFlux)
        .flatMap(repository::save)
        .then();
  }

  private Flux<NutritionIntakeEntity> fillDailyIntake(Gender gender, RecordEntity record) {
    return Flux.concat(
        vitaminService.findAll()
            .flatMap(vitamin -> {
              BigDecimal lowerBoundIntake = gender.equals(Gender.MALE) ? vitamin.getMaleLowerBoundIntake() : vitamin.getFemaleLowerBoundIntake();
              BigDecimal upperBoundIntake = gender.equals(Gender.MALE) ? vitamin.getMaleHigherBoundIntake() : vitamin.getFemaleHigherBoundIntake();
              return createNutritionIntake(vitamin.getName(), "Vitamin", vitamin.getMeasure(), lowerBoundIntake, upperBoundIntake, record);
            }),
        electrolyteService.findAll()
            .flatMap(electrolyte -> {
              BigDecimal lowerBoundIntake = gender.equals(Gender.MALE) ? electrolyte.getMaleLowerBoundIntake() : electrolyte.getFemaleLowerBoundIntake();
              BigDecimal upperBoundIntake = gender.equals(Gender.MALE) ? electrolyte.getMaleHigherBoundIntake() : electrolyte.getFemaleHigherBoundIntake();
              return createNutritionIntake(electrolyte.getName(), "Electrolyte", electrolyte.getMeasure(), lowerBoundIntake, upperBoundIntake, record);
            })
    );
  }

  private Mono<NutritionIntakeEntity> createNutritionIntake(String nutrientName, String nutrientType, String measurement,
                                                            BigDecimal lowerBoundIntake, BigDecimal upperBoundIntake, RecordEntity record) {
    NutritionIntakeEntity nutritionIntakeEntity = new NutritionIntakeEntity();
    nutritionIntakeEntity.setNutrientName(nutrientName);
    nutritionIntakeEntity.setNutrientType(nutrientType);
    nutritionIntakeEntity.setMeasurement(measurement);
    nutritionIntakeEntity.setLowerBoundIntake(lowerBoundIntake);
    nutritionIntakeEntity.setUpperBoundIntake(upperBoundIntake);
    nutritionIntakeEntity.setRecordId(record.getId());
    return Mono.just(nutritionIntakeEntity);
  }

  private Boolean inactiveState(WorkoutState state) {
    return state.equals(WorkoutState.SEDENTARY) || state.equals(WorkoutState.LIGHTLY_ACTIVE);
  }

  public Mono<NutritionIntakeEntity> findByNutrientNameAndRecordId(Long recordId, String name) {
    return repository.findByNutrientNameAndRecordId(recordId, name);
  }

  public Mono<NutritionIntakeEntity> save(NutritionIntakeEntity data) {
    return repository.save(data);
  }

  public Flux<NutritionIntakeEntity> findAllByRecordId(Long recordId) {
    return repository.findAllByRecordId(recordId);
  }
}
