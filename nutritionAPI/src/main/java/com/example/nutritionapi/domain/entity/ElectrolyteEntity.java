package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "electrolytes")
public class ElectrolyteEntity{
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

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> healthConsiderations;

    @OneToOne(cascade = CascadeType.PERSIST)
    private DailyIntakeEntity female;
    @OneToOne(cascade = CascadeType.PERSIST)
    private DailyIntakeEntity male;

    @Column
    private BigDecimal dailyTook;

    public BigDecimal getDailyTook() {
        return dailyTook;
    }

    public ElectrolyteEntity setDailyTook(BigDecimal dailyTook) {
        this.dailyTook = dailyTook;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ElectrolyteEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ElectrolyteEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ElectrolyteEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PairEntity> getFunctions() {
        return functions;
    }

    public ElectrolyteEntity setFunctions(List<PairEntity> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairEntity> getSources() {
        return sources;
    }

    public ElectrolyteEntity setSources(List<PairEntity> sources) {
        this.sources = sources;
        return this;
    }

    public DailyIntakeEntity getFemale() {
        return female;
    }

    public ElectrolyteEntity setFemale(DailyIntakeEntity female) {
        this.female = female;
        return this;
    }

    public DailyIntakeEntity getMale() {
        return male;
    }

    public ElectrolyteEntity setMale(DailyIntakeEntity male) {
        this.male = male;
        return this;
    }

    @ManyToOne
    private RecordEntity record;

    public RecordEntity getRecord() {
        return record;
    }

    public ElectrolyteEntity setRecord(RecordEntity record) {
        this.record = record;
        return this;
    }

    public List<PairEntity> getHealthConsiderations() {
        return healthConsiderations;
    }

    public ElectrolyteEntity setHealthConsiderations(List<PairEntity> healthConsiderations) {
        this.healthConsiderations = healthConsiderations;
        return this;
    }
}
