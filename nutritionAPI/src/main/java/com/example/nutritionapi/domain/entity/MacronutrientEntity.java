package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "macronutrients")
public class MacronutrientEntity extends NutritionBaseEntity{

    @OneToMany
    private List<PairEntity> types;

    @ManyToOne
    private RecordEntity record;

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


}
