package com.example.nutrition_api.domain.record.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;

@Getter
@Entity
@Table(name = "nutrition_intakes")
public class NutritionIntake {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nutrition_name")
  private String nutrientName;
  @Column(name = "nutrition_type")
  private String nutrientType;
  @Column(name = "daily_calories")
  private BigDecimal dailyConsumed = BigDecimal.ZERO;
  @Column(name = "lower_bound_intake")
  private BigDecimal lowerBoundIntake;
  @Column(name = "upper_bound_intake")
  private BigDecimal upperBoundIntake;
  @Column(name = "measurement")
  private String measurement;
  @ManyToOne
  @JoinColumn(name = "record_id")
  private Record record;

  public NutritionIntake setDailyConsumed(BigDecimal dailyConsumed) {
    this.dailyConsumed = dailyConsumed;
    return this;
  }

  public NutritionIntake setRecord(Record record) {
    this.record = record;
    return this;
  }

  public NutritionIntake setId(Long id) {
    this.id = id;
    return this;
  }

  public NutritionIntake setNutrientName(String nutrientName) {
    this.nutrientName = nutrientName;
    return this;
  }

  public NutritionIntake setNutrientType(String nutrientType) {
    this.nutrientType = nutrientType;
    return this;
  }

  public NutritionIntake setLowerBoundIntake(BigDecimal lowerBoundIntake) {
    this.lowerBoundIntake = lowerBoundIntake;
    return this;
  }

  public NutritionIntake setUpperBoundIntake(BigDecimal upperBoundIntake) {
    this.upperBoundIntake = upperBoundIntake;
    return this;
  }

  public NutritionIntake setMeasurement(String measurement) {
    this.measurement = measurement;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NutritionIntake that = (NutritionIntake) o;
    return Objects.equals(id, that.id) && Objects.equals(nutrientName, that.nutrientName)
        && Objects.equals(nutrientType, that.nutrientType) && Objects.equals(dailyConsumed,
        that.dailyConsumed) && Objects.equals(lowerBoundIntake, that.lowerBoundIntake)
        && Objects.equals(upperBoundIntake, that.upperBoundIntake) && Objects.equals(measurement,
        that.measurement) && Objects.equals(record, that.record);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nutrientName, nutrientType, dailyConsumed, lowerBoundIntake,
        upperBoundIntake, measurement, record);
  }
}