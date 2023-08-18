package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vitamins")
public class VitaminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> functions;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> sources;

    @Column
    private BigDecimal maleLowerBoundIntake;
    @Column
    private BigDecimal maleHigherBoundIntake;
    @Column
    private BigDecimal femaleLowerBoundIntake;
    @Column
    private BigDecimal femaleHigherBoundIntake;
    @Column
    private String measure;


    public Long getId() {
        return id;
    }

    public VitaminEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VitaminEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VitaminEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PairEntity> getFunctions() {
        return functions;
    }

    public VitaminEntity setFunctions(List<PairEntity> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairEntity> getSources() {
        return sources;
    }

    public VitaminEntity setSources(List<PairEntity> sources) {
        this.sources = sources;
        return this;
    }

    public BigDecimal getMaleLowerBoundIntake() {
        return maleLowerBoundIntake;
    }

    public VitaminEntity setMaleLowerBoundIntake(BigDecimal maleLowerBoundIntake) {
        this.maleLowerBoundIntake = maleLowerBoundIntake;
        return this;
    }

    public BigDecimal getMaleHigherBoundIntake() {
        return maleHigherBoundIntake;
    }

    public VitaminEntity setMaleHigherBoundIntake(BigDecimal maleHigherBoundIntake) {
        this.maleHigherBoundIntake = maleHigherBoundIntake;
        return this;
    }

    public BigDecimal getFemaleLowerBoundIntake() {
        return femaleLowerBoundIntake;
    }

    public VitaminEntity setFemaleLowerBoundIntake(BigDecimal femaleLowerBoundIntake) {
        this.femaleLowerBoundIntake = femaleLowerBoundIntake;
        return this;
    }

    public BigDecimal getFemaleHigherBoundIntake() {
        return femaleHigherBoundIntake;
    }

    public VitaminEntity setFemaleHigherBoundIntake(BigDecimal femaleHigherBoundIntake) {
        this.femaleHigherBoundIntake = femaleHigherBoundIntake;
        return this;
    }

    public String getMeasure() {
        return measure;
    }

    public VitaminEntity setMeasure(String measure) {
        this.measure = measure;
        return this;
    }
}