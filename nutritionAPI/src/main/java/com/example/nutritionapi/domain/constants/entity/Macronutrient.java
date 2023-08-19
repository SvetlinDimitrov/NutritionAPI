package com.example.nutritionapi.domain.constants.entity;

import java.util.List;

public class Macronutrient {
    private String name;
    private String description;
    private Double activeState ;
    private Double inactiveState ;
    private List<Pair> functions;
    private List<Pair> sources;
    private List<Pair> types;
    private List<Pair> dietaryConsiderations;


    public String getName() {
        return name;
    }

    public Macronutrient setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Macronutrient setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getActiveState() {
        return activeState;
    }

    public Macronutrient setActiveState(Double activeState) {
        this.activeState = activeState;
        return this;
    }

    public Double getInactiveState() {
        return inactiveState;
    }

    public Macronutrient setInactiveState(Double inactiveState) {
        this.inactiveState = inactiveState;
        return this;
    }

    public List<Pair> getFunctions() {
        return functions;
    }

    public Macronutrient setFunctions(List<Pair> functions) {
        this.functions = functions;
        return this;
    }

    public List<Pair> getSources() {
        return sources;
    }

    public Macronutrient setSources(List<Pair> sources) {
        this.sources = sources;
        return this;
    }


    public List<Pair> getTypes() {
        return types;
    }

    public Macronutrient setTypes(List<Pair> types) {
        this.types = types;
        return this;
    }

    public List<Pair> getDietaryConsiderations() {
        return dietaryConsiderations;
    }

    public Macronutrient setDietaryConsiderations(List<Pair> dietaryConsiderations) {
        this.dietaryConsiderations = dietaryConsiderations;
        return this;
    }
}
