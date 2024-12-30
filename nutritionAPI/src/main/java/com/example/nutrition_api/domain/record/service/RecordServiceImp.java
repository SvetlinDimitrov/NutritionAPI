package com.example.nutrition_api.domain.record.service;

import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.NUTRITION_TO_UPDATE_NOT_FOUND;
import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.RECORD_NOT_FOUND;

import com.example.nutrition_api.domain.record.dto.NutrientUpdateRequest;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.entity.NutritionIntake;
import com.example.nutrition_api.domain.record.entity.Record;
import com.example.nutrition_api.domain.record.repository.RecordRepository;
import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.domain.users.service.UserService;
import com.example.nutrition_api.infrastructure.exceptions.throwable.NotFoundException;
import com.example.nutrition_api.infrastructure.mappers.NutritionIntakeMapper;
import com.example.nutrition_api.infrastructure.mappers.RecordMapper;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecordServiceImp implements RecordService {

  private UserService userService;
  private final RecordRepository recordRepository;
  private final NutrientIntakeService nutrientIntakeService;
  private final RecordMapper recordMapper;
  private final NutritionIntakeMapper nutrientIntakeMapper;

  @Autowired
  public void setUserService(@Lazy UserService userService) {
    this.userService = userService;
  }

  public List<RecordView> getAll(Long userId) {

    return userService.findById(userId)
        .getRecords()
        .stream()
        .map(recordMapper::toView)
        .toList();
  }

  public RecordView getById(Long id) {
    return recordRepository.findById(id)
        .map(recordMapper::toView)
        .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));
  }

  @Transactional
  public NutritionIntakeView updateById(Long day, NutrientUpdateRequest dto, User user) {

    Record record = user.getRecords()
        .stream()
        .filter(r -> r.getId().equals(day))
        .findAny()
        .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));

    NutritionIntake intake = record.getDailyIntakeViews()
        .stream()
        .filter(nutrient -> nutrient.getNutrientName().equals(dto.name()))
        .findFirst()
        .orElseThrow(() -> new NotFoundException(NUTRITION_TO_UPDATE_NOT_FOUND));

    intake.setDailyConsumed(intake.getDailyConsumed().add(dto.measure()));
    recordRepository.save(record);

    return nutrientIntakeMapper.toView(intake);
  }

  @Transactional
  public void deleteById(Long id, User user) {

    user.getRecords()
        .stream()
        .filter(r -> r.getId().equals(id))
        .findAny()
        .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));

    recordRepository.deleteById(id);
  }

  public RecordView addNewRecordByUserId(Long userId) {
    User user = userService.findById(userId);

    Record record = create(user);

    user.getRecords().add(record);
    userService.save(user);

    return recordMapper.toView(user.getRecords()
        .getLast());
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
