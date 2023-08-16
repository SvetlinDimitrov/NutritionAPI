package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "records")
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "record")
    private List<VitaminEntity> vitamins;

    @OneToMany(mappedBy = "record")
    private List<MacronutrientEntity> macros;

    @OneToMany(mappedBy = "record")
    private List<ElectrolyteEntity> electrolytes;

    @Column
    private BigDecimal dailyCalories;

    @ManyToOne
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public BigDecimal getDailyCalories() {
        return dailyCalories;
    }

    public RecordEntity setDailyCalories(BigDecimal dailyCalories) {
        this.dailyCalories = dailyCalories;
        return this;
    }

    public RecordEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public List<VitaminEntity> getVitamins() {
        return vitamins;
    }

    public RecordEntity setVitamins(List<VitaminEntity> vitamins) {
        this.vitamins = vitamins;
        return this;
    }

    public List<MacronutrientEntity> getMacros() {
        return macros;
    }

    public RecordEntity setMacros(List<MacronutrientEntity> macros) {
        this.macros = macros;
        return this;
    }

    public List<ElectrolyteEntity> getElectrolytes() {
        return electrolytes;
    }

    public RecordEntity setElectrolytes(List<ElectrolyteEntity> electrolytes) {
        this.electrolytes = electrolytes;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public RecordEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
