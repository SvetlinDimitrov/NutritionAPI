package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "electrolytes")
public class ElectrolyteEntity extends NutritionBaseEntity {

    @ManyToOne
    private RecordEntity record;

    public RecordEntity getRecord() {
        return record;
    }

    public ElectrolyteEntity setRecord(RecordEntity record) {
        this.record = record;
        return this;
    }

}
