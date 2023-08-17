package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.entity.VitaminEntity;

import java.util.List;

public class VitaminView {
    private String name;
    private String description;
    private List<PairView> functions;
    private List<PairView> sources;
    private DailyIntakeView female;
    private DailyIntakeView male;

    public VitaminView() {}

    public VitaminView(VitaminEntity entity) {

        this.name = entity.getName();
        this.description = entity.getDescription();
        this.functions = entity.getFunctions().stream().map(PairView::new).toList();
        this.sources = entity.getFunctions().stream().map(PairView::new).toList();
        this.female = new DailyIntakeView(entity.getFemale());
        this.male = new DailyIntakeView(entity.getMale());
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

    public List<PairView> getFunctions() {
        return functions;
    }

    public VitaminView setFunctions(List<PairView> functions) {
        this.functions = functions;
        return this;
    }

    public List<PairView> getSources() {
        return sources;
    }

    public VitaminView setSources(List<PairView> sources) {
        this.sources = sources;
        return this;
    }

    public DailyIntakeView getFemale() {
        return female;
    }

    public VitaminView setFemale(DailyIntakeView female) {
        this.female = female;
        return this;
    }

    public DailyIntakeView getMale() {
        return male;
    }

    public VitaminView setMale(DailyIntakeView male) {
        this.male = male;
        return this;
    }
}
