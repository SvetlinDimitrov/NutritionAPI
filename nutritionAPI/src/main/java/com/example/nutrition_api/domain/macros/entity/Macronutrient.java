package com.example.nutrition_api.domain.macros.entity;

import com.example.nutrition_api.domain.shared.entity.Pair;
import java.util.List;
import lombok.Getter;

@Getter
public class Macronutrient {

  private String name;
  private String description;
  private Double activeState;
  private Double inactiveState;
  private List<Pair> functions;
  private List<Pair> sources;
  private List<Pair> types;
  private List<Pair> dietaryConsiderations;


  public Macronutrient setName(String name) {
    this.name = name;
    return this;
  }

  public Macronutrient setDescription(String description) {
    this.description = description;
    return this;
  }

  public Macronutrient setActiveState(Double activeState) {
    this.activeState = activeState;
    return this;
  }

  public Macronutrient setInactiveState(Double inactiveState) {
    this.inactiveState = inactiveState;
    return this;
  }

  public Macronutrient setFunctions(List<Pair> functions) {
    this.functions = functions;
    return this;
  }

  public Macronutrient setSources(List<Pair> sources) {
    this.sources = sources;
    return this;
  }


  public Macronutrient setTypes(List<Pair> types) {
    this.types = types;
    return this;
  }

  public Macronutrient setDietaryConsiderations(List<Pair> dietaryConsiderations) {
    this.dietaryConsiderations = dietaryConsiderations;
    return this;
  }
}
