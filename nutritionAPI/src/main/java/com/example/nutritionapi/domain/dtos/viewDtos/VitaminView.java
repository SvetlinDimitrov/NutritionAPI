package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.constants.entity.Vitamin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class VitaminView {
    private String name;
    private String description;
    private List<PairView> functions;
    private List<PairView> sources;
    private BigDecimal maleLowerBoundIntake;
    private BigDecimal maleHigherBoundIntake;
    private BigDecimal femaleLowerBoundIntake;
    private BigDecimal femaleHigherBoundIntake;
    private String measure;

    public VitaminView() {}

    public VitaminView(Vitamin entity) {

        this.name = entity.getName();
        this.description = entity.getDescription();
        this.functions = entity.getFunctions().stream().map(PairView::new).toList();
        this.sources = entity.getFunctions().stream().map(PairView::new).toList();
        this.maleHigherBoundIntake = entity.getMaleHigherBoundIntake();
        this.maleLowerBoundIntake = entity.getMaleLowerBoundIntake();
        this.femaleHigherBoundIntake = entity.getFemaleHigherBoundIntake();
        this.femaleLowerBoundIntake = entity.getFemaleLowerBoundIntake();
        this.measure = entity.getMeasure();
    }

    public String getName() {
        return name;
    }

    public VitaminView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VitaminView setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PairView> getFunctions() {
        return functions;
    }

    public VitaminView setFunctions(List<PairView> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairView> getSources() {
        return sources;
    }

    public VitaminView setSources(List<PairView> sources) {
        this.sources = sources;
        return this;
    }

    public BigDecimal getMaleLowerBoundIntake() {
        return maleLowerBoundIntake;
    }

    public VitaminView setMaleLowerBoundIntake(BigDecimal maleLowerBoundIntake) {
        this.maleLowerBoundIntake = maleLowerBoundIntake;
        return this;
    }

    public BigDecimal getMaleHigherBoundIntake() {
        return maleHigherBoundIntake;
    }

    public VitaminView setMaleHigherBoundIntake(BigDecimal maleHigherBoundIntake) {
        this.maleHigherBoundIntake = maleHigherBoundIntake;
        return this;
    }

    public BigDecimal getFemaleLowerBoundIntake() {
        return femaleLowerBoundIntake;
    }

    public VitaminView setFemaleLowerBoundIntake(BigDecimal femaleLowerBoundIntake) {
        this.femaleLowerBoundIntake = femaleLowerBoundIntake;
        return this;
    }

    public BigDecimal getFemaleHigherBoundIntake() {
        return femaleHigherBoundIntake;
    }

    public VitaminView setFemaleHigherBoundIntake(BigDecimal femaleHigherBoundIntake) {
        this.femaleHigherBoundIntake = femaleHigherBoundIntake;
        return this;
    }

    public String getMeasure() {
        return measure;
    }

    public VitaminView setMeasure(String measure) {
        this.measure = measure;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VitaminView that = (VitaminView) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(functions, that.functions) && Objects.equals(sources, that.sources) && Objects.equals(maleLowerBoundIntake, that.maleLowerBoundIntake) && Objects.equals(maleHigherBoundIntake, that.maleHigherBoundIntake) && Objects.equals(femaleLowerBoundIntake, that.femaleLowerBoundIntake) && Objects.equals(femaleHigherBoundIntake, that.femaleHigherBoundIntake) && Objects.equals(measure, that.measure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, functions, sources, maleLowerBoundIntake, maleHigherBoundIntake, femaleLowerBoundIntake, femaleHigherBoundIntake, measure);
    }
}
