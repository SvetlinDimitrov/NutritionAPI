package com.example.nutrition_api.domain.record.entity;

import com.example.nutrition_api.domain.users.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @Column(name = "daily_calories")
    private BigDecimal dailyCalories;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
