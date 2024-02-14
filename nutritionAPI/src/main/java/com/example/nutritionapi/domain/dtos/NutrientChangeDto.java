package com.example.nutritionapi.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NutrientChangeDto(
        @NotBlank
        String name,

        @NotNull
        BigDecimal measure
) {}
