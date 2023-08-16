package com.example.nutritionapi.domain.entity;

import com.example.nutritionapi.domain.constants.Gender;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@MappedSuperclass
public class NutritionBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    @OneToMany
    private List<PairEntity> functions;
    @OneToMany
    private List<PairEntity> sources;

    @Column
    private BigDecimal recommendedIntake;
    @Column
    private BigDecimal dalyIntake;

    @Column
    @Enumerated
    private Gender gender;

    public String getName() {
        return name;
    }

    public NutritionBaseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getRecommendedIntake() {
        return recommendedIntake;
    }

    public NutritionBaseEntity setRecommendedIntake(BigDecimal recommendedIntake) {
        this.recommendedIntake = recommendedIntake;
        return this;
    }

    public BigDecimal getDalyIntake() {
        return dalyIntake;
    }

    public NutritionBaseEntity setDalyIntake(BigDecimal dalyIntake) {
        this.dalyIntake = dalyIntake;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public NutritionBaseEntity setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NutritionBaseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NutritionBaseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PairEntity> getFunctions() {
        return functions;
    }

    public NutritionBaseEntity setFunctions(List<PairEntity> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairEntity> getSources() {
        return sources;
    }

    public NutritionBaseEntity setSources(List<PairEntity> sources) {
        this.sources = sources;
        return this;
    }

}
