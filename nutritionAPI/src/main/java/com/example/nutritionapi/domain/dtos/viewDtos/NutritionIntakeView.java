package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.entity.NutritionIntake;

import java.math.BigDecimal;

public class NutritionIntakeView {
    private Long id;

    private String nutrientName;

    private String nutrientType;

    private BigDecimal lowerBoundIntake;

    private BigDecimal upperBoundIntake;
    private BigDecimal dailyConsumed;

    private String measurement;

    private Long recordId;

    public NutritionIntakeView() {}

    public NutritionIntakeView(NutritionIntake entity) {
        this.id = entity.getId();
        this.nutrientName = entity.getNutrientName();
        this.nutrientType = entity.getNutrientType();
        this.lowerBoundIntake = entity.getLowerBoundIntake();
        this.upperBoundIntake = entity.getUpperBoundIntake();
        this.measurement = entity.getMeasurement();
        this.recordId = entity.getRecord().getId();
        this.dailyConsumed = entity.getDailyConsumed();
    }

    public BigDecimal getDailyConsumed() {
        return dailyConsumed;
    }

    public NutritionIntakeView setDailyConsumed(BigDecimal dailyConsumed) {
        this.dailyConsumed = dailyConsumed;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NutritionIntakeView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public NutritionIntakeView setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
        return this;
    }

    public String getNutrientType() {
        return nutrientType;
    }

    public NutritionIntakeView setNutrientType(String nutrientType) {
        this.nutrientType = nutrientType;
        return this;
    }

    public BigDecimal getLowerBoundIntake() {
        return lowerBoundIntake;
    }

    public NutritionIntakeView setLowerBoundIntake(BigDecimal lowerBoundIntake) {
        this.lowerBoundIntake = lowerBoundIntake;
        return this;
    }

    public BigDecimal getUpperBoundIntake() {
        return upperBoundIntake;
    }

    public NutritionIntakeView setUpperBoundIntake(BigDecimal upperBoundIntake) {
        this.upperBoundIntake = upperBoundIntake;
        return this;
    }

    public String getMeasurement() {
        return measurement;
    }

    public NutritionIntakeView setMeasurement(String measurement) {
        this.measurement = measurement;
        return this;
    }

    public Long getRecordId() {
        return recordId;
    }

    public NutritionIntakeView setRecordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }
}
