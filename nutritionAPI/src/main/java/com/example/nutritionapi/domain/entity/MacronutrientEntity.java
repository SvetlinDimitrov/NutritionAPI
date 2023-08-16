package com.example.nutritionapi.domain.entity;

import com.example.nutritionapi.domain.constants.WorkoutState;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "macronutrients")
public class MacronutrientEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Double activeState ;
    @Column
    private Double inactiveState ;
    @Column
    private BigDecimal dailyTook;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> functions;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> sources;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> types;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> dietaryConsiderations;

    @ManyToOne
    private RecordEntity record;

    public BigDecimal getDailyTook() {
        return dailyTook;
    }

    public MacronutrientEntity setDailyTook(BigDecimal dailyTook) {
        this.dailyTook = dailyTook;
        return this;
    }

    public RecordEntity getRecord() {
        return record;
    }

    public MacronutrientEntity setRecord(RecordEntity record) {
        this.record = record;
        return this;
    }

    public MacronutrientEntity setTypes(List<PairEntity> types) {
        this.types = types;
        return this;
    }

    public List<PairEntity> getTypes() {
        return types;
    }

    public Long getId() {
        return id;
    }

    public MacronutrientEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MacronutrientEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MacronutrientEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PairEntity> getFunctions() {
        return functions;
    }

    public MacronutrientEntity setFunctions(List<PairEntity> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairEntity> getSources() {
        return sources;
    }

    public MacronutrientEntity setSources(List<PairEntity> sources) {
        this.sources = sources;
        return this;
    }

    public Double getActiveState() {
        return activeState;
    }

    public MacronutrientEntity setActiveState(Double activeState) {
        this.activeState = activeState;
        return this;
    }

    public Double getInactiveState() {
        return inactiveState;
    }

    public MacronutrientEntity setInactiveState(Double inactiveState) {
        this.inactiveState = inactiveState;
        return this;
    }

    public List<PairEntity> getDietaryConsiderations() {
        return dietaryConsiderations;
    }

    public MacronutrientEntity setDietaryConsiderations(List<PairEntity> dietaryConsiderations) {
        this.dietaryConsiderations = dietaryConsiderations;
        return this;
    }
}
