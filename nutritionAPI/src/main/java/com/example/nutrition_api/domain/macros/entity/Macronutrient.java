package com.example.nutrition_api.domain.macros.entity;

import com.example.nutrition_api.domain.shared.entity.Pair;
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
public class Macronutrient {

  private String name;
  private String description;
  private Double activeState;
  private Double inactiveState;
  private List<Pair> functions;
  private List<Pair> sources;
  private List<Pair> types;
  private List<Pair> dietaryConsiderations;
}
