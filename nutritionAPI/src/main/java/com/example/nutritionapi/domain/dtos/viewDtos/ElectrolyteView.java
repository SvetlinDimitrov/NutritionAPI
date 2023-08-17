package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.entity.ElectrolyteEntity;

import java.util.List;

public class ElectrolyteView {

    private String name;
    private String description;
    private List<PairView> functions;
    private List<PairView> sources;
    private List<PairView> healthConsiderations;
    private DailyIntakeView female;
    private DailyIntakeView male;

    public ElectrolyteView() {}
    public ElectrolyteView(ElectrolyteEntity entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.functions = entity.getFunctions().stream().map(PairView::new).toList();
        this.sources = entity.getSources().stream().map(PairView::new).toList();
        this.healthConsiderations = entity.getHealthConsiderations().stream().map(PairView::new).toList();
        this.female = new DailyIntakeView(entity.getFemale());
        this.male = new DailyIntakeView(entity.getMale());
    }

    public String getName() {
        return name;
    }

    public ElectrolyteView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ElectrolyteView setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PairView> getFunctions() {
        return functions;
    }

    public ElectrolyteView setFunctions(List<PairView> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairView> getSources() {
        return sources;
    }

    public ElectrolyteView setSources(List<PairView> sources) {
        this.sources = sources;
        return this;
    }

    public List<PairView> getHealthConsiderations() {
        return healthConsiderations;
    }

    public ElectrolyteView setHealthConsiderations(List<PairView> healthConsiderations) {
        this.healthConsiderations = healthConsiderations;
        return this;
    }

    public DailyIntakeView getFemale() {
        return female;
    }

    public ElectrolyteView setFemale(DailyIntakeView female) {
        this.female = female;
        return this;
    }

    public DailyIntakeView getMale() {
        return male;
    }

    public ElectrolyteView setMale(DailyIntakeView male) {
        this.male = male;
        return this;
    }
}
