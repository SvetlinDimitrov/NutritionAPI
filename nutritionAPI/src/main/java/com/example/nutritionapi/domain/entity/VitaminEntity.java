package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vitamins")
public class VitaminEntity extends NutritionBaseEntity{
    @Column
    private String deficiency;

    @Column
    private String excess;

    @ManyToOne
    private RecordEntity record;

    public RecordEntity getRecord() {
        return record;
    }

    public VitaminEntity setRecord(RecordEntity record) {
        this.record = record;
        return this;
    }

    public VitaminEntity() {
    }


    public String getDeficiency() {
        return deficiency;
    }

    public VitaminEntity setDeficiency(String deficiency) {
        this.deficiency = deficiency;
        return this;
    }

    public String getExcess() {
        return excess;
    }

    public VitaminEntity setExcess(String excess) {
        this.excess = excess;
        return this;
    }

}