package com.example.nutritionapi.domain.dtos;

import com.example.nutritionapi.domain.entity.DailyIntake;
import com.example.nutritionapi.domain.entity.PairEntity;
import com.example.nutritionapi.domain.entity.RecordEntity;
import com.example.nutritionapi.domain.entity.VitaminEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class VitaminView {
    private Long id;
    private String name;
    private String description;
    private BigDecimal dailyTook;
    private List<PairEntity> functions;
    private List<PairEntity> sources;
    private DailyIntake female;
    private DailyIntake male;

    public VitaminView() {}

    public VitaminView(VitaminEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.dailyTook = entity.getDailyTook();
        this.functions = entity.getFunctions();
        this.sources = entity.getSources();
        this.female = entity.getFemale();
        this.male = entity.getMale();
    }

    public Long getId() {
        return id;
    }

    public VitaminView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VitaminView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VitaminView setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getDailyTook() {
        return dailyTook;
    }

    public VitaminView setDailyTook(BigDecimal dailyTook) {
        this.dailyTook = dailyTook;
        return this;
    }

    public List<PairEntity> getFunctions() {
        return functions;
    }

    public VitaminView setFunctions(List<PairEntity> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairEntity> getSources() {
        return sources;
    }

    public VitaminView setSources(List<PairEntity> sources) {
        this.sources = sources;
        return this;
    }

    public DailyIntake getFemale() {
        return female;
    }

    public VitaminView setFemale(DailyIntake female) {
        this.female = female;
        return this;
    }

    public DailyIntake getMale() {
        return male;
    }

    public VitaminView setMale(DailyIntake male) {
        this.male = male;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VitaminView that = (VitaminView) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(dailyTook, that.dailyTook) && Objects.equals(functions, that.functions) && Objects.equals(sources, that.sources) && Objects.equals(female, that.female) && Objects.equals(male, that.male);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dailyTook, functions, sources, female, male);
    }

}
