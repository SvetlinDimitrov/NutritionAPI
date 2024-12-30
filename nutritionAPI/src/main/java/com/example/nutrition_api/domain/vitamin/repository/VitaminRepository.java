package com.example.nutrition_api.domain.vitamin.repository;

import com.example.nutrition_api.domain.vitamin.entity.Vitamin;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class VitaminRepository {

  private final Map<String, Vitamin> vitaminMap = new LinkedHashMap<>();

  public void save(Vitamin vitamin) {
    vitaminMap.put(vitamin.getName(), vitamin);
  }

  public Optional<Vitamin> get(String name) {
    return Optional.ofNullable(vitaminMap.get(name));
  }

  public List<Vitamin> findAll() {
    return vitaminMap.values()
        .stream()
        .toList();
  }
}