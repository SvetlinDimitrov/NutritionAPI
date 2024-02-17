package com.api.reactive_nutritionapi.domain.dtos.viewDtos;

import com.api.reactive_nutritionapi.domain.constants.entity.Macronutrient;

import java.util.List;

public record MacronutrientView(
        String name,
        String description,
        Double activeState,
        Double inactiveState,
        List<PairView> functions,
        List<PairView> sources,
        List<PairView> types,
        List<PairView> dietaryConsiderations
) {
  public static MacronutrientView toView(Macronutrient entity) {
    return new MacronutrientView(
        entity.getName(),
        entity.getDescription(),
        entity.getActiveState(),
        entity.getInactiveState(),
        entity.getFunctions().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
        entity.getSources().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
        entity.getTypes().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
        entity.getDietaryConsiderations().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList()
    );
  }
}
