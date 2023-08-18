package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.entity.RecordEntity;

import java.math.BigDecimal;
import java.util.List;

public class RecordView {
    private Long day;
    private List<NutritionIntakeView> dailyIntakeViews;
    private BigDecimal dailyCaloriesToConsume;
    private Long userID;
    private String userName;

    public RecordView(RecordEntity record) {
        this.day = record.getId();
        this.dailyIntakeViews = record.getDailyIntakeViews().stream().map(NutritionIntakeView::new).toList();
        this.dailyCaloriesToConsume = record.getDailyCalories();
        this.userID = record.getUser().getId();
        this.userName = record.getUser().getUsername();
    }

    public Long getId() {
        return day;
    }

    public RecordView setId(Long id) {
        this.day = id;
        return this;
    }

    public List<NutritionIntakeView> getDailyIntakeViews() {
        return dailyIntakeViews;
    }

    public RecordView setDailyIntakeViews(List<NutritionIntakeView> dailyIntakeViews) {
        this.dailyIntakeViews = dailyIntakeViews;
        return this;
    }

    public BigDecimal getDailyCaloriesToConsume() {
        return dailyCaloriesToConsume;
    }

    public RecordView setDailyCaloriesToConsume(BigDecimal dailyCaloriesToConsume) {
        this.dailyCaloriesToConsume = dailyCaloriesToConsume;
        return this;
    }

    public Long getUserID() {
        return userID;
    }

    public RecordView setUserID(Long userID) {
        this.userID = userID;
        return this;
    }

    public Long getDay() {
        return day;
    }

    public RecordView setDay(Long day) {
        this.day = day;
        return this;
    }


    public String getUserName() {
        return userName;
    }

    public RecordView setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
