package com.example.nutritionapi.domain.entity;

import com.example.nutritionapi.domain.constants.Gender;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dailyIntakes")
public class DailyIntakeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal lowerBound;
    @Column
    private BigDecimal UpperBound;

    @Column
    private String measureUnit;

    @Column
    @Enumerated
    private Gender gender;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public DailyIntakeEntity setLowerBound(BigDecimal lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public BigDecimal getUpperBound() {
        return UpperBound;
    }

    public DailyIntakeEntity setUpperBound(BigDecimal upperBound) {
        UpperBound = upperBound;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public DailyIntakeEntity setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public DailyIntakeEntity setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
        return this;
    }
}
