package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.constants.entity.Macronutrient;

import java.util.List;

public class MacronutrientView {

    private String name;
    private String description;
    private Double activeState;
    private Double inactiveState ;
    private List<PairView> functions;
    private List<PairView> sources;
    private List<PairView> types;
    private List<PairView> dietaryConsiderations;

    public MacronutrientView() {}
    public MacronutrientView(Macronutrient entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.activeState = entity.getActiveState();
        this.inactiveState =entity.getInactiveState();
        this.functions = entity.getFunctions().stream().map(PairView::new).toList();
        this.sources = entity.getSources().stream().map(PairView::new).toList();
        this.types = entity.getTypes().stream().map(PairView::new).toList();
        this.dietaryConsiderations = entity.getDietaryConsiderations().stream().map(PairView::new).toList();
    }

    public String getName() {
        return name;
    }

    public MacronutrientView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MacronutrientView setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getActiveState() {
        return activeState;
    }

    public MacronutrientView setActiveState(Double activeState) {
        this.activeState = activeState;
        return this;
    }

    public Double getInactiveState() {
        return inactiveState;
    }

    public MacronutrientView setInactiveState(Double inactiveState) {
        this.inactiveState = inactiveState;
        return this;
    }

    public List<PairView> getFunctions() {
        return functions;
    }

    public MacronutrientView setFunctions(List<PairView> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairView> getSources() {
        return sources;
    }

    public MacronutrientView setSources(List<PairView> sources) {
        this.sources = sources;
        return this;
    }

    public List<PairView> getTypes() {
        return types;
    }

    public MacronutrientView setTypes(List<PairView> types) {
        this.types = types;
        return this;
    }

    public List<PairView> getDietaryConsiderations() {
        return dietaryConsiderations;
    }

    public MacronutrientView setDietaryConsiderations(List<PairView> dietaryConsiderations) {
        this.dietaryConsiderations = dietaryConsiderations;
        return this;
    }
}
