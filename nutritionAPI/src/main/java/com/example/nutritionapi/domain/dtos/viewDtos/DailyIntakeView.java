package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.constants.Gender;
import com.example.nutritionapi.domain.entity.DailyIntakeEntity;

import java.math.BigDecimal;

public class DailyIntakeView {

    private BigDecimal lowerBound;
    private BigDecimal upperBound;
    private String measureUnit;
    private Gender gender;

    public DailyIntakeView() {}
    public DailyIntakeView(DailyIntakeEntity entity) {
        this.lowerBound = entity.getLowerBound();
        this.upperBound = entity.getUpperBound();
        this.measureUnit = entity.getMeasureUnit();
        this.gender = entity.getGender();
    }

    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public DailyIntakeView setLowerBound(BigDecimal lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public DailyIntakeView setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public DailyIntakeView setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public DailyIntakeView setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}

