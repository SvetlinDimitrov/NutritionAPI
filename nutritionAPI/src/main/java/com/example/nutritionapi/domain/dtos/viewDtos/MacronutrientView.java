package com.example.nutritionapi.domain.dtos.viewDtos;

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
}
