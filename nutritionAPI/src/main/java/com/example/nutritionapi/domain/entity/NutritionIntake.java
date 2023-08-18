package com.example.nutritionapi.domain.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "nutritionIntake")
public class NutritionIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nutrientName;
    @Column
    private String nutrientType;
    @Column
    private BigDecimal dailyConsumed = BigDecimal.ZERO;
    @Column
    private BigDecimal lowerBoundIntake;
    @Column
    private BigDecimal upperBoundIntake;
    @Column
    private  String measurement;
    @ManyToOne
    private RecordEntity record;

    public RecordEntity getRecord() {
        return record;
    }

    public BigDecimal getDailyConsumed() {
        return dailyConsumed;
    }

    public NutritionIntake setDailyConsumed(BigDecimal dailyConsumed) {
        this.dailyConsumed = dailyConsumed;
        return this;
    }

    public NutritionIntake setRecord(RecordEntity record) {
        this.record = record;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NutritionIntake setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public NutritionIntake setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
        return this;
    }

    public String getNutrientType() {
        return nutrientType;
    }

    public NutritionIntake setNutrientType(String nutrientType) {
        this.nutrientType = nutrientType;
        return this;
    }

    public BigDecimal getLowerBoundIntake() {
        return lowerBoundIntake;
    }

    public NutritionIntake setLowerBoundIntake(BigDecimal lowerBoundIntake) {
        this.lowerBoundIntake = lowerBoundIntake;
        return this;
    }

    public BigDecimal getUpperBoundIntake() {
        return upperBoundIntake;
    }

    public NutritionIntake setUpperBoundIntake(BigDecimal upperBoundIntake) {
        this.upperBoundIntake = upperBoundIntake;
        return this;
    }

    public String getMeasurement() {
        return measurement;
    }

    public NutritionIntake setMeasurement(String measurement) {
        this.measurement = measurement;
        return this;
    }
}
