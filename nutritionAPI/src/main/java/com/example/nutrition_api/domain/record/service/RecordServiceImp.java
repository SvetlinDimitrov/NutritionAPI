package com.example.nutrition_api.domain.record.service;

import com.example.nutrition_api.domain.record.dto.NutrientUpdateRequest;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.entity.NutritionIntake;
import com.example.nutrition_api.domain.record.entity.Record;
import com.example.nutrition_api.domain.record.repository.RecordRepository;
import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.repository.UserRepository;
import com.example.nutrition_api.infrastructure.exceptions.IncorrectNutrientChangeException;
import com.example.nutrition_api.infrastructure.exceptions.RecordNotFoundException;
import com.example.nutrition_api.infrastructure.mappers.ViewConverter;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecordServiceImp implements RecordService {

  private final RecordRepository recordRepository;
  private final UserRepository userRepository;
  private final NutrientIntakeService nutrientIntakeService;
  private final ViewConverter converter;

  public RecordServiceImp(RecordRepository recordRepository, UserRepository userRepository,
      NutrientIntakeService nutrientIntakeService, ViewConverter converter) {
    this.recordRepository = recordRepository;
    this.userRepository = userRepository;
    this.nutrientIntakeService = nutrientIntakeService;
    this.converter = converter;
  }

  public List<RecordView> getAll(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UsernameNotFoundException(userId.toString()));
    return user.getRecords().stream()
        .map(converter::toView)
        .toList();
  }

  public RecordView getById(Long id) throws RecordNotFoundException {
    return recordRepository.findById(id)
        .map(converter::toView)
        .orElseThrow(() -> new RecordNotFoundException(id.toString()));
  }

  @Transactional
  public NutritionIntakeView updateById(Long day, NutrientUpdateRequest dto, User user)
      throws RecordNotFoundException, IncorrectNutrientChangeException {

    Record record = user.getRecords()
        .stream()
        .filter(r -> r.getId().equals(day))
        .findAny()
        .orElseThrow(() -> new RecordNotFoundException(day.toString()));

    NutritionIntake intake = record.getDailyIntakeViews()
        .stream()
        .filter(nutrient -> nutrient.getNutrientName().equals(dto.name()))
        .findFirst()
        .orElseThrow(() -> new IncorrectNutrientChangeException("catch me"));

    intake.setDailyConsumed(intake.getDailyConsumed().add(dto.measure()));
    recordRepository.save(record);

    return converter.toView(intake);
  }

  @Transactional
  public void deleteById(Long id, User user) throws RecordNotFoundException {

    user.getRecords()
        .stream()
        .filter(r -> r.getId().equals(id))
        .findAny()
        .orElseThrow(() -> new RecordNotFoundException(id.toString()));

    recordRepository.deleteById(id);
  }

  public RecordView addNewRecordByUserId(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UsernameNotFoundException(userId.toString()));

    Record record = create(user);

    user.getRecords().add(record);
    userRepository.save(user);

    return converter.toView(user.getRecords().get(user.getRecords().size() - 1));
  }

  public Record create(User user) {
    Record record = new Record();
    record.setUser(user);
    BigDecimal BMR = getBmr(user);

    BigDecimal caloriesPerDay = switch (user.getWorkoutState()) {
      case SEDENTARY -> BMR.multiply(new BigDecimal("1.2"));
      case LIGHTLY_ACTIVE -> BMR.multiply(new BigDecimal("1.375"));
      case MODERATELY_ACTIVE -> BMR.multiply(new BigDecimal("1.55"));
      case VERY_ACTIVE -> BMR.multiply(new BigDecimal("1.725"));
      case SUPER_ACTIVE -> BMR.multiply(new BigDecimal("1.9"));
    };

    record.setDailyCalories(caloriesPerDay);
    record.setDailyIntakeViews(nutrientIntakeService
        .create(user.getGender(), caloriesPerDay, user.getWorkoutState(), record));
    return record;
  }

  private BigDecimal getBmr(User user) {
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
}
