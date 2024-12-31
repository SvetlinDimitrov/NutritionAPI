package com.example.nutrition_api.infrastructure.mappers;

import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.entity.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {NutritionIntakeMapper.class})
public interface RecordMapper {

  @Mapping(source = "dailyCalories", target = "dailyCaloriesToConsume")
  @Mapping(source = "user.id", target = "userID")
  @Mapping(source = "user.username", target = "userName")
  RecordView toView(Record entity);
}
