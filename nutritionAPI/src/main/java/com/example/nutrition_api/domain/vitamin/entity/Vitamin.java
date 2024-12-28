package com.example.nutrition_api.domain.vitamin.entity;

import com.example.nutrition_api.domain.shared.entity.Pair;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Vitamin {

  private String name;
  private String description;
  private List<Pair> functions;
  private List<Pair> sources;
  private BigDecimal maleLowerBoundIntake;
  private BigDecimal maleHigherBoundIntake;
  private BigDecimal femaleLowerBoundIntake;
  private BigDecimal femaleHigherBoundIntake;
  private String measure;
}