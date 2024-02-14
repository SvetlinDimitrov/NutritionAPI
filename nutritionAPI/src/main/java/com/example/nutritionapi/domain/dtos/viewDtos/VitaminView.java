package com.example.nutritionapi.domain.dtos.viewDtos;

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
}