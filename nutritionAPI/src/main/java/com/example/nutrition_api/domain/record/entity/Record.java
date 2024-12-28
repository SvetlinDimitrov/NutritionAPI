package com.example.nutrition_api.domain.record.entity;

import com.example.nutrition_api.domain.users.entity.User;
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
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "record" , cascade = {CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE})
    private List<NutritionIntake> dailyIntakeViews;

    @Column(name = "daily_calories")
    private BigDecimal dailyCalories;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public BigDecimal getDailyCalories() {
        return dailyCalories;
    }

    public Record setDailyCalories(BigDecimal dailyCalories) {
        this.dailyCalories = dailyCalories;
        return this;
    }

    public Record setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Record setUser(User user) {
        this.user = user;
        return this;
    }

    public List<NutritionIntake> getDailyIntakeViews() {
        return dailyIntakeViews;
    }

    public Record setDailyIntakeViews(List<NutritionIntake> dailyIntakeViews) {
        this.dailyIntakeViews = dailyIntakeViews;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(id, record.id) && Objects.equals(dailyIntakeViews, record.dailyIntakeViews) && Objects.equals(dailyCalories, record.dailyCalories) && Objects.equals(user, record.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dailyIntakeViews, dailyCalories, user);
    }
}
