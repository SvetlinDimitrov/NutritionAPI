package com.example.nutrition_api.domain.macros.dto;

import com.example.nutrition_api.domain.shared.dto.PairView;
import java.util.List;

public record MacronutrientView(String name, String description, Double activeState, Double inactiveState, List<PairView> functions, List<PairView> sources, List<PairView> types, List<PairView> dietaryConsiderations) {}
