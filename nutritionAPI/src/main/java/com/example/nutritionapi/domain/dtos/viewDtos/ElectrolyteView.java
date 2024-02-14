package com.example.nutritionapi.domain.dtos.viewDtos;

import java.math.BigDecimal;
import java.util.List;

public record ElectrolyteView(String name,
                              String description,
                              List<PairView> functions,
                              List<PairView> sources,
                              List<PairView> healthConsiderations,
                              BigDecimal maleLowerBoundIntake,
                              BigDecimal maleHigherBoundIntake,
                              BigDecimal femaleLowerBoundIntake,
                              BigDecimal femaleHigherBoundIntake,
                              String measure
) {

}
