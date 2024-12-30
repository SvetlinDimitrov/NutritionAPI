package com.example.nutrition_api.domain.electrolyte.repository;

import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ElectrolyteRepository {

  private final Map<String, Electrolyte> electrolyteEntityMap = new LinkedHashMap<>();

  public void save(Electrolyte electrolyte) {
    electrolyteEntityMap.put(electrolyte.getName(), electrolyte);
  }

  public Optional<Electrolyte> get(String name) {
    return Optional.ofNullable(electrolyteEntityMap.get(name));
  }

  public List<Electrolyte> findAll() {
    return electrolyteEntityMap.values()
        .stream()
        .toList();
  }
}