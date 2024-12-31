package com.example.nutrition_api.infrastructure.mappers;

import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.entity.NutritionIntake;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NutritionIntakeMapper {

  @Mapping(source = "record.id", target = "recordId")
  NutritionIntakeView toView(NutritionIntake entity);
}
