package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.entity.RecordEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordView that = (RecordView) o;
        return Objects.equals(day, that.day) && Objects.equals(dailyIntakeViews, that.dailyIntakeViews) && Objects.equals(dailyCaloriesToConsume, that.dailyCaloriesToConsume) && Objects.equals(userID, that.userID) && Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, dailyIntakeViews, dailyCaloriesToConsume, userID, userName);
    }
}
