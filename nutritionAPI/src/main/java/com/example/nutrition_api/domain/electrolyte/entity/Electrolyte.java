package com.example.nutrition_api.domain.electrolyte.entity;

import com.example.nutrition_api.domain.shared.entity.Pair;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;

@Getter
public class Electrolyte {

  private String name;
  private String description;
  private List<Pair> functions;
  private List<Pair> sources;
  private List<Pair> healthConsiderations;
  private BigDecimal maleLowerBoundIntake;
  private BigDecimal maleHigherBoundIntake;
  private BigDecimal femaleLowerBoundIntake;
  private BigDecimal femaleHigherBoundIntake;
  private String measure;

  public Electrolyte setName(String name) {
    this.name = name;
    return this;
  }

  public Electrolyte setDescription(String description) {
    this.description = description;
    return this;
  }

  public Electrolyte setFunctions(List<Pair> functions) {
    this.functions = functions;
    return this;
  }

  public Electrolyte setSources(List<Pair> sources) {
    this.sources = sources;
    return this;
  }

  public Electrolyte setHealthConsiderations(List<Pair> healthConsiderations) {
    this.healthConsiderations = healthConsiderations;
    return this;
  }

  public Electrolyte setMaleLowerBoundIntake(BigDecimal maleLowerBoundIntake) {
    this.maleLowerBoundIntake = maleLowerBoundIntake;
    return this;
  }

  public Electrolyte setMaleHigherBoundIntake(BigDecimal maleHigherBoundIntake) {
    this.maleHigherBoundIntake = maleHigherBoundIntake;
    return this;
  }

  public Electrolyte setFemaleLowerBoundIntake(BigDecimal femaleLowerBoundIntake) {
    this.femaleLowerBoundIntake = femaleLowerBoundIntake;
    return this;
  }

  public Electrolyte setFemaleHigherBoundIntake(BigDecimal femaleHigherBoundIntake) {
    this.femaleHigherBoundIntake = femaleHigherBoundIntake;
    return this;
  }

  public Electrolyte setMeasure(String measure) {
    this.measure = measure;
    return this;
  }
}