package com.example.nutrition_api.domain.record.service;

import com.example.nutrition_api.domain.record.dto.NutrientUpdateRequest;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.entity.Record;
import com.example.nutrition_api.domain.users.entity.User;
import com.example.nutrition_api.infrastructure.exceptions.IncorrectNutrientChangeException;
import com.example.nutrition_api.infrastructure.exceptions.RecordNotFoundException;
import java.util.List;

public interface RecordService {

  List<RecordView> getAll(Long userId);

  RecordView getById(Long id) throws RecordNotFoundException;

  NutritionIntakeView updateById(Long day, NutrientUpdateRequest dto, User user)
      throws RecordNotFoundException, IncorrectNutrientChangeException;

  void deleteById(Long id, User user) throws RecordNotFoundException;

  RecordView addNewRecordByUserId(Long userId);

  Record create(User user);
}
