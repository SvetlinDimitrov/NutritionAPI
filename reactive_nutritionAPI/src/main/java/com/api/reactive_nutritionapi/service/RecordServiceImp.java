package com.api.reactive_nutritionapi.service;

import com.api.reactive_nutritionapi.domain.constants.enums.Gender;
import com.api.reactive_nutritionapi.domain.dtos.NutrientChangeDto;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.NutritionIntakeView;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.RecordView;
import com.api.reactive_nutritionapi.domain.entity.RecordEntity;
import com.api.reactive_nutritionapi.domain.entity.UserEntity;
import com.api.reactive_nutritionapi.exceptions.IncorrectNutrientChangeException;
import com.api.reactive_nutritionapi.exceptions.RecordNotFoundException;
import com.api.reactive_nutritionapi.exceptions.UserException;
import com.api.reactive_nutritionapi.repos.RecordRepository;
import com.api.reactive_nutritionapi.repos.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class RecordServiceImp {
  private final RecordRepository recordRepository;
  private final UserRepository userRepository;
  private final NutrientIntakeService nutrientIntakeService;

  public RecordServiceImp(RecordRepository recordRepository, UserRepository userRepository, NutrientIntakeService nutrientIntakeService) {
    this.recordRepository = recordRepository;
    this.userRepository = userRepository;
    this.nutrientIntakeService = nutrientIntakeService;
  }

  public Flux<RecordView> getAllViewsByUserId(String userEmail) {
    return userRepository
        .findByEmail(userEmail)
        .flatMapMany(user ->
            recordRepository.findAllByUserId(user.getId())
                .switchIfEmpty(Flux.error(new UserException("No record found with userId " + user.getId())))
        )
        .flatMap(recordView ->
            nutrientIntakeService.findAllByRecordId(recordView.getId())
                .collectList()
                .map(list -> RecordView.toView(recordView, list))
        );
  }


  public Mono<RecordView> getViewByRecordIdAndUserId(Long recordId, String userEmail) {
    return userRepository
        .findByEmail(userEmail)
        .flatMap(user -> recordRepository.findByIdAndUserId(user.getId(), recordId))
        .switchIfEmpty(Mono.error(new RecordNotFoundException(recordId.toString())))
        .flatMap(
            data ->
                nutrientIntakeService.findAllByRecordId(data.getId())
                    .collectList()
                    .map(nutritionIntakeEntities -> RecordView.toView(data, nutritionIntakeEntities))
        );
  }

  @Transactional
  public Mono<NutritionIntakeView> updateRecordById(Long recordId, String userEmail, NutrientChangeDto dto) throws RecordNotFoundException, IncorrectNutrientChangeException {
    return
        userRepository.findByEmail(userEmail)
            .flatMap(user -> recordRepository.findByIdAndUserId(recordId, user.getId()))
            .switchIfEmpty(Mono.error(new RecordNotFoundException("No record found with id " + recordId)))
            .flatMap(record -> nutrientIntakeService.findByNutrientNameAndRecordId(record.getId(), dto.name()))
            .switchIfEmpty(Mono.error(new IncorrectNutrientChangeException("Nutrient not found for record with ID: " + recordId)))
            .flatMap(data -> {
              data.setDailyConsumed(data.getDailyConsumed().add(dto.measure()));
              return nutrientIntakeService.save(data)
                  .map(NutritionIntakeView::toView);
            });
  }

  public Mono<Void> createRecord(UserEntity user) {
    return Mono.fromSupplier(() -> {
          RecordEntity record = new RecordEntity();
          record.setUserId(user.getId());
          BigDecimal BMR = getBmr(user);

          BigDecimal caloriesPerDay = switch (user.getWorkoutState()) {
            case SEDENTARY -> BMR.multiply(new BigDecimal("1.2"));
            case LIGHTLY_ACTIVE -> BMR.multiply(new BigDecimal("1.375"));
            case MODERATELY_ACTIVE -> BMR.multiply(new BigDecimal("1.55"));
            case VERY_ACTIVE -> BMR.multiply(new BigDecimal("1.725"));
            case SUPER_ACTIVE -> BMR.multiply(new BigDecimal("1.9"));
          };
          record.setDailyCalories(caloriesPerDay);
          return record;
        })
        .flatMap(recordRepository::save)
        .flatMap(record -> nutrientIntakeService.create(user.getGender(), record.getDailyCalories(), user.getWorkoutState(), record))
        .then();
  }

  private BigDecimal getBmr(UserEntity user) {
    BigDecimal BMR;

    if (user.getGender().equals(Gender.MALE)) {
      BMR = new BigDecimal("88.362")
          .add(new BigDecimal("13.397").multiply(user.getKilograms()))
          .add(new BigDecimal("4.799").multiply(user.getHeight()))
          .subtract(new BigDecimal("5.677").add(new BigDecimal(user.getAge())));
    } else {
      BMR = new BigDecimal("447.593 ")
          .add(new BigDecimal("9.247").multiply(user.getKilograms()))
          .add(new BigDecimal("3.098").multiply(user.getHeight()))
          .subtract(new BigDecimal("4.330").add(new BigDecimal(user.getAge())));
    }
    return BMR;
  }

  @Transactional
  public Mono<Void> deleteById(Long recordId, String email) throws RecordNotFoundException {
    return userRepository.findByEmail(email)
        .flatMap(user -> recordRepository
            .deleteByIdAndUserId(recordId, user.getId()))
        .flatMap(result -> {
          if (result) {
            return Mono.empty(); // Return empty Mono if deletion is successful
          } else {
            return Mono.error(new RecordNotFoundException("Record not found with ID: " + recordId));
          }
        });
  }
}
