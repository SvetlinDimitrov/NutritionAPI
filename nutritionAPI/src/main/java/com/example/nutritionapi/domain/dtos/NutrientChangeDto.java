package com.example.nutritionapi.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class NutrientChangeDto {
    @NotBlank
    private String name;

    @NotNull
    private BigDecimal measure;

    public String getName() {
        return name;
    }

    public NutrientChangeDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getMeasure() {
        return measure;
    }

    public NutrientChangeDto setMeasure(BigDecimal measure) {
        this.measure = measure;
        return this;
    }
}
