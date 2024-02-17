package com.api.reactive_nutritionapi.domain.dtos.viewDtos;

import com.api.reactive_nutritionapi.domain.constants.entity.Electrolyte;

import java.math.BigDecimal;
import java.util.List;

public record ElectrolyteView(
    String name,
    String description,
    List<PairView> functions,
    List<PairView> sources,
    List<PairView> healthConsiderations,
    BigDecimal maleLowerBoundIntake,
    BigDecimal maleHigherBoundIntake,
    BigDecimal femaleLowerBoundIntake,
    BigDecimal femaleHigherBoundIntake,
    String measure) {
  public static ElectrolyteView toView(Electrolyte entity){
    return new ElectrolyteView(
        entity.getName(),
        entity.getDescription(),
        entity.getFunctions().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
        entity.getSources().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
        entity.getHealthConsiderations().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
        entity.getMaleLowerBoundIntake(),
        entity.getMaleHigherBoundIntake(),
        entity.getFemaleLowerBoundIntake(),
        entity.getFemaleHigherBoundIntake(),
        entity.getMeasure());
  }
}
