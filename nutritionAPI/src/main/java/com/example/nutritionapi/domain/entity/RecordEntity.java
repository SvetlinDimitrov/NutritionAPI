package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordEntity record = (RecordEntity) o;
        return Objects.equals(id, record.id) && Objects.equals(dailyIntakeViews, record.dailyIntakeViews) && Objects.equals(dailyCalories, record.dailyCalories) && Objects.equals(user, record.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dailyIntakeViews, dailyCalories, user);
    }
}
