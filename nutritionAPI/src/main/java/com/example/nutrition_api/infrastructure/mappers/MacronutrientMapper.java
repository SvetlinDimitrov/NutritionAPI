package com.example.nutrition_api.infrastructure.mappers;

import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.domain.macros.entity.Macronutrient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PairMapper.class})
public interface MacronutrientMapper {

  MacronutrientView toView(Macronutrient entity);
}
