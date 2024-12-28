package com.example.nutrition_api.domain.record.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record NutrientUpdateRequest(
    @NotBlank
    String name,

    @NotNull
    BigDecimal measure
) {

}
