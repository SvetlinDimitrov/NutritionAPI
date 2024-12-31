package com.example.nutrition_api.domain.record.service;

import com.example.nutrition_api.domain.record.dto.NutrientUpdateRequest;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.entity.Record;
import com.example.nutrition_api.domain.users.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService {

  Page<RecordView> getAll(Pageable pageable);

  RecordView getById(Long id);

  NutritionIntakeView updateById(Long day, NutrientUpdateRequest dto);

  void deleteById(Long id);

  RecordView addNewRecordByUserId();

  Record create(User user);

  boolean existsByRecordIdAndUserEmail(Long recordId, String userEmail);
}
