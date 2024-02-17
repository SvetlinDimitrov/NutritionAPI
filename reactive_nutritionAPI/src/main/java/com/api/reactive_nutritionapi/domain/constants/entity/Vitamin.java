package com.api.reactive_nutritionapi.domain.constants.entity;

import java.math.BigDecimal;
import java.util.List;
public class Vitamin {

    private String name;
    private String description;
    private List<Pair> functions;
    private List<Pair> sources;
    private BigDecimal maleLowerBoundIntake;
    private BigDecimal maleHigherBoundIntake;
    private BigDecimal femaleLowerBoundIntake;
    private BigDecimal femaleHigherBoundIntake;
    private String measure;

    public String getName() {
        return name;
    }

    public Vitamin setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Vitamin setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Pair> getFunctions() {
        return functions;
    }

    public Vitamin setFunctions(List<Pair> functions) {
        this.functions = functions;
        return this;
    }

    public List<Pair> getSources() {
        return sources;
    }

    public Vitamin setSources(List<Pair> sources) {
        this.sources = sources;
        return this;
    }

    public BigDecimal getMaleLowerBoundIntake() {
        return maleLowerBoundIntake;
    }

    public Vitamin setMaleLowerBoundIntake(BigDecimal maleLowerBoundIntake) {
        this.maleLowerBoundIntake = maleLowerBoundIntake;
        return this;
    }

    public BigDecimal getMaleHigherBoundIntake() {
        return maleHigherBoundIntake;
    }

    public Vitamin setMaleHigherBoundIntake(BigDecimal maleHigherBoundIntake) {
        this.maleHigherBoundIntake = maleHigherBoundIntake;
        return this;
    }

    public BigDecimal getFemaleLowerBoundIntake() {
        return femaleLowerBoundIntake;
    }

    public Vitamin setFemaleLowerBoundIntake(BigDecimal femaleLowerBoundIntake) {
        this.femaleLowerBoundIntake = femaleLowerBoundIntake;
        return this;
    }

    public BigDecimal getFemaleHigherBoundIntake() {
        return femaleHigherBoundIntake;
    }

    public Vitamin setFemaleHigherBoundIntake(BigDecimal femaleHigherBoundIntake) {
        this.femaleHigherBoundIntake = femaleHigherBoundIntake;
        return this;
    }

    public String getMeasure() {
        return measure;
    }

    public Vitamin setMeasure(String measure) {
        this.measure = measure;
        return this;
    }
}