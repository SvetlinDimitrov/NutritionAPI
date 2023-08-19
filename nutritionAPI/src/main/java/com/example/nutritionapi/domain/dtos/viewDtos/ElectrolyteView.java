package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.constants.entity.Electrolyte;

import java.math.BigDecimal;
import java.util.List;

public class ElectrolyteView {

    private String name;
    private String description;
    private List<PairView> functions;
    private List<PairView> sources;
    private List<PairView> healthConsiderations;
    private BigDecimal maleLowerBoundIntake;
    private BigDecimal maleHigherBoundIntake;
    private BigDecimal femaleLowerBoundIntake;
    private BigDecimal femaleHigherBoundIntake;
    private String measure;

    public ElectrolyteView() {}
    public ElectrolyteView(Electrolyte entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.functions = entity.getFunctions().stream().map(PairView::new).toList();
        this.sources = entity.getSources().stream().map(PairView::new).toList();
        this.healthConsiderations = entity.getHealthConsiderations().stream().map(PairView::new).toList();
        this.maleHigherBoundIntake = entity.getMaleHigherBoundIntake();
        this.maleLowerBoundIntake = entity.getMaleLowerBoundIntake();
        this.femaleHigherBoundIntake = entity.getFemaleHigherBoundIntake();
        this.femaleLowerBoundIntake = entity.getFemaleLowerBoundIntake();
        this.measure = entity.getMeasure();
    }

    public String getName() {
        return name;
    }

    public ElectrolyteView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ElectrolyteView setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PairView> getFunctions() {
        return functions;
    }

    public ElectrolyteView setFunctions(List<PairView> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairView> getSources() {
        return sources;
    }

    public ElectrolyteView setSources(List<PairView> sources) {
        this.sources = sources;
        return this;
    }

    public List<PairView> getHealthConsiderations() {
        return healthConsiderations;
    }

    public ElectrolyteView setHealthConsiderations(List<PairView> healthConsiderations) {
        this.healthConsiderations = healthConsiderations;
        return this;
    }

    public BigDecimal getMaleLowerBoundIntake() {
        return maleLowerBoundIntake;
    }

    public ElectrolyteView setMaleLowerBoundIntake(BigDecimal maleLowerBoundIntake) {
        this.maleLowerBoundIntake = maleLowerBoundIntake;
        return this;
    }

    public BigDecimal getMaleHigherBoundIntake() {
        return maleHigherBoundIntake;
    }

    public ElectrolyteView setMaleHigherBoundIntake(BigDecimal maleHigherBoundIntake) {
        this.maleHigherBoundIntake = maleHigherBoundIntake;
        return this;
    }

    public BigDecimal getFemaleLowerBoundIntake() {
        return femaleLowerBoundIntake;
    }

    public ElectrolyteView setFemaleLowerBoundIntake(BigDecimal femaleLowerBoundIntake) {
        this.femaleLowerBoundIntake = femaleLowerBoundIntake;
        return this;
    }

    public BigDecimal getFemaleHigherBoundIntake() {
        return femaleHigherBoundIntake;
    }

    public ElectrolyteView setFemaleHigherBoundIntake(BigDecimal femaleHigherBoundIntake) {
        this.femaleHigherBoundIntake = femaleHigherBoundIntake;
        return this;
    }

    public String getMeasure() {
        return measure;
    }

    public ElectrolyteView setMeasure(String measure) {
        this.measure = measure;
        return this;
    }
}
