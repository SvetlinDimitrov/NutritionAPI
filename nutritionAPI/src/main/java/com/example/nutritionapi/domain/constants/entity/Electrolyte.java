package com.example.nutritionapi.domain.constants.entity;

import java.math.BigDecimal;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public Electrolyte setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Electrolyte setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Pair> getFunctions() {
        return functions;
    }

    public Electrolyte setFunctions(List<Pair> functions) {
        this.functions = functions;
        return this;
    }

    public List<Pair> getSources() {
        return sources;
    }

    public Electrolyte setSources(List<Pair> sources) {
        this.sources = sources;
        return this;
    }

    public List<Pair> getHealthConsiderations() {
        return healthConsiderations;
    }

    public Electrolyte setHealthConsiderations(List<Pair> healthConsiderations) {
        this.healthConsiderations = healthConsiderations;
        return this;
    }

    public BigDecimal getMaleLowerBoundIntake() {
        return maleLowerBoundIntake;
    }

    public Electrolyte setMaleLowerBoundIntake(BigDecimal maleLowerBoundIntake) {
        this.maleLowerBoundIntake = maleLowerBoundIntake;
        return this;
    }

    public BigDecimal getMaleHigherBoundIntake() {
        return maleHigherBoundIntake;
    }

    public Electrolyte setMaleHigherBoundIntake(BigDecimal maleHigherBoundIntake) {
        this.maleHigherBoundIntake = maleHigherBoundIntake;
        return this;
    }

    public BigDecimal getFemaleLowerBoundIntake() {
        return femaleLowerBoundIntake;
    }

    public Electrolyte setFemaleLowerBoundIntake(BigDecimal femaleLowerBoundIntake) {
        this.femaleLowerBoundIntake = femaleLowerBoundIntake;
        return this;
    }

    public BigDecimal getFemaleHigherBoundIntake() {
        return femaleHigherBoundIntake;
    }

    public Electrolyte setFemaleHigherBoundIntake(BigDecimal femaleHigherBoundIntake) {
        this.femaleHigherBoundIntake = femaleHigherBoundIntake;
        return this;
    }

    public String getMeasure() {
        return measure;
    }

    public Electrolyte setMeasure(String measure) {
        this.measure = measure;
        return this;
    }
}