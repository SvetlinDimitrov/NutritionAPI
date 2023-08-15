package com.example.nutritionapi.domain.entity;

import com.example.nutritionapi.domain.constants.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vitamins")
public class Vitamin extends BaseEntity {

    @Column
    private String description;
    @ElementCollection
    @CollectionTable(name = "functions_vitamin_table")
    @Column(name = "functions_vitamin")
    private List<String> functions;
    @ElementCollection
    @CollectionTable(name = "sources_vitamin_table")
    @Column(name = "sources_vitamins")
    private List<String> sources;
    @Column
    private BigDecimal recommendedIntake;
    @Column
    private String deficiency;
    @Column
    private String excess;
    @Column
    private BigDecimal dalyIntake;

    public Vitamin() {
    }

    public BigDecimal getDalyIntake() {
        return dalyIntake;
    }

    public Vitamin setDalyIntake(BigDecimal dalyIntake) {
        this.dalyIntake = dalyIntake;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Vitamin setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public Vitamin setFunctions(List<String> functions) {
        this.functions = functions;
        return this;
    }

    public List<String> getSources() {
        return sources;
    }

    public Vitamin setSources(List<String> sources) {
        this.sources = sources;
        return this;
    }

    public BigDecimal getRecommendedIntake() {
        return recommendedIntake;
    }

    public Vitamin setRecommendedIntake(BigDecimal recommendedIntake) {
        this.recommendedIntake = recommendedIntake;
        return this;
    }

    public String getDeficiency() {
        return deficiency;
    }

    public Vitamin setDeficiency(String deficiency) {
        this.deficiency = deficiency;
        return this;
    }

    public String getExcess() {
        return excess;
    }

    public Vitamin setExcess(String excess) {
        this.excess = excess;
        return this;
    }
}
