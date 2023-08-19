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

    @OneToMany(mappedBy = "record" , cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE})
    private List<NutritionIntakeEntity> dailyIntakeViews;

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

    public UserEntity getUser() {
        return user;
    }

    public RecordEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public List<NutritionIntakeEntity> getDailyIntakeViews() {
        return dailyIntakeViews;
    }

    public RecordEntity setDailyIntakeViews(List<NutritionIntakeEntity> dailyIntakeViews) {
        this.dailyIntakeViews = dailyIntakeViews;
        return this;
    }
}
