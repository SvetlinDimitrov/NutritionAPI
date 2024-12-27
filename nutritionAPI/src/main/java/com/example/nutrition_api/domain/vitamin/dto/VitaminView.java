package com.example.nutrition_api.domain.vitamin.dto;

import com.example.nutrition_api.domain.shared.dto.PairView;
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