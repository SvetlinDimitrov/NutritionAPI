package com.example.nutrition_api.domain.macros.repository;

import com.example.nutrition_api.domain.macros.entity.Macronutrient;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MacronutrientRepository {

  private final Map<String, Macronutrient> macronutrientMap = new LinkedHashMap<>();

  public void save(Macronutrient macronutrient) {
    macronutrientMap.put(macronutrient.getName(), macronutrient);
  }

  public Optional<Macronutrient> get(String name) {
    return Optional.ofNullable(macronutrientMap.get(name));
  }

  public List<Macronutrient> findAll() {
    return macronutrientMap.values()
        .stream()
        .toList();
  }
}