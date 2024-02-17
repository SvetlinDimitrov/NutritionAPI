package com.api.reactive_nutritionapi.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.List;


@Table(name = "records")
@Getter
@Setter
public class RecordEntity {

    @Id
    private Long id;
    @Column("daily_calories")
    private BigDecimal dailyCalories;
    @Column("user_id")
    private Long userId;


}
