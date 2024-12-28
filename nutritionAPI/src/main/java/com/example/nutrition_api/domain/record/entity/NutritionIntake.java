package com.example.nutrition_api.domain.record.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "nutrition_intakes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"record"})
@ToString(exclude = {"record"})
@Builder
public class NutritionIntake {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nutrition_name")
  private String nutrientName;
  @Column(name = "nutrition_type")
  private String nutrientType;
  @Column(name = "daily_calories")
  private BigDecimal dailyConsumed = BigDecimal.ZERO;
  @Column(name = "lower_bound_intake")
  private BigDecimal lowerBoundIntake;
  @Column(name = "upper_bound_intake")
  private BigDecimal upperBoundIntake;
  @Column(name = "measurement")
  private String measurement;
  @ManyToOne
  @JoinColumn(name = "record_id")
  private Record record;
}
