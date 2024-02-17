package com.api.reactive_nutritionapi.domain.dtos.viewDtos;

import com.api.reactive_nutritionapi.domain.constants.entity.Vitamin;

import java.math.BigDecimal;
import java.util.List;

public record VitaminView(
        String name,
        String description,
        List<PairView> functions,
        List<PairView> sources,
        BigDecimal maleLowerBoundIntake,
        BigDecimal maleHigherBoundIntake,
        BigDecimal femaleLowerBoundIntake,
        BigDecimal femaleHigherBoundIntake,
        String measure
) {
  public static VitaminView toView(Vitamin entity) {
    return new VitaminView(
        entity.getName(),
        entity.getDescription(),
        entity.getFunctions().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
        entity.getSources().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
        entity.getMaleLowerBoundIntake(),
        entity.getMaleHigherBoundIntake(),
        entity.getFemaleLowerBoundIntake(),
        entity.getFemaleHigherBoundIntake(),
        entity.getMeasure()
    );
  }
}