package com.example.nutrition_api.domain.record.service;

import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.RECORD_NOT_FOUND;

import com.example.nutrition_api.domain.record.dto.NutrientUpdateRequest;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.entity.Record;
import com.example.nutrition_api.domain.record.repository.RecordRepository;
import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.domain.users.enums.Gender;
import com.example.nutrition_api.infrastructure.exceptions.throwable.NotFoundException;
import com.example.nutrition_api.infrastructure.mappers.RecordMapper;
import com.example.nutrition_api.infrastructure.security.service.UserDetailsServiceImpl;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordServiceImp implements RecordService {

  private final UserDetailsServiceImpl securityService;
  private final NutrientIntakeService nutrientIntakeService;
  private final RecordRepository repository;
  private final RecordMapper mapper;

  public Page<RecordView> getAll(Pageable pageable) {
    var loggedInUser = securityService.getLoggedInUser();

    return repository.findAllByUser_Email(loggedInUser.getEmail(), pageable)
        .map(mapper::toView);
  }

  public RecordView getById(Long id) {
    return repository.findById(id)
        .map(mapper::toView)
        .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));
  }

  public NutritionIntakeView updateById(Long recordId, NutrientUpdateRequest dto) {
    var record = repository.findById(recordId)
        .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));

    return nutrientIntakeService.update(dto, record.getId());
  }

  public void deleteById(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException(RECORD_NOT_FOUND);
    }

    repository.deleteById(id);
  }

  public RecordView addNewRecordByUserId() {
    var loggedInUser = securityService.getLoggedInUser();

    Record record = create(loggedInUser);

    return mapper.toView(repository.save(record));
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
    return repository.save(record);
  }

  public boolean existsByRecordIdAndUserEmail(Long recordId, String userEmail) {
    return repository.existsByIdAndUser_Email(recordId, userEmail);
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
