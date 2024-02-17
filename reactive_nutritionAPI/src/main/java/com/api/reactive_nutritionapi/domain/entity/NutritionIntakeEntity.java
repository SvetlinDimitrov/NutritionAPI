package com.api.reactive_nutritionapi.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("nutrition_intakes")
@Getter
@Setter
public class NutritionIntakeEntity {
    @Id
    private Long id;
    @Column("nutrition_name")
    private String nutrientName;
    @Column("nutrition_type")
    private String nutrientType;
    @Column("daily_calories")
    private BigDecimal dailyConsumed = BigDecimal.ZERO;
    @Column("lower_bound_intake")
    private BigDecimal lowerBoundIntake;
    @Column("upper_bound_intake")
    private BigDecimal upperBoundIntake;
    @Column("measurement")
    private String measurement;
    @Column("record_id")
    private Long recordId;
}
