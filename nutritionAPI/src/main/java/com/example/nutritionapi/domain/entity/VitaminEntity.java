package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vitamins")
public class VitaminEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private BigDecimal dailyTook;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> functions;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PairEntity> sources;

    @OneToOne(cascade = CascadeType.PERSIST)
    private DailyIntake female;
    @OneToOne(cascade = CascadeType.PERSIST)
    private DailyIntake male;
    @ManyToOne
    private RecordEntity record;

    public VitaminEntity() {
    }

    public BigDecimal getDailyTook() {
        return dailyTook;
    }

    public VitaminEntity setDailyTook(BigDecimal dailyTook) {
        this.dailyTook = dailyTook;
        return this;
    }

    public RecordEntity getRecord() {
        return record;
    }

    public VitaminEntity setRecord(RecordEntity record) {
        this.record = record;
        return this;
    }

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

    public DailyIntake getFemale() {
        return female;
    }

    public VitaminEntity setFemale(DailyIntake female) {
        this.female = female;
        return this;
    }

    public DailyIntake getMale() {
        return male;
    }

    public VitaminEntity setMale(DailyIntake male) {
        this.male = male;
        return this;
    }
}