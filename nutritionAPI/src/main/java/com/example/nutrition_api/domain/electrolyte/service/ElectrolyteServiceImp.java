package com.example.nutrition_api.domain.electrolyte.service;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import com.example.nutrition_api.infrastructure.exceptions.ElectrolyteNotFoundException;
import com.example.nutrition_api.infrastructure.mappers.ViewConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectrolyteServiceImp implements ElectrolyteService {

  private final Map<String, Electrolyte> electrolyteEntityMap = new LinkedHashMap<>();
  private final ViewConverter converter;

  public List<Electrolyte> findAll() {
    return electrolyteEntityMap.values().stream().toList();
  }

  public List<ElectrolyteView> getAll() {
    return electrolyteEntityMap
        .values()
        .stream()
        .map(converter::toView)
        .toList();
  }

  public ElectrolyteView getByName(String name) throws ElectrolyteNotFoundException {

    if (electrolyteEntityMap.containsKey(name)) {
      return converter.toView(electrolyteEntityMap.get(name));
    }
    throw new ElectrolyteNotFoundException(name);
  }

  public String getAllElectrolytesNames() {
    return String.join(",", electrolyteEntityMap.keySet());
  }

  @PostConstruct
  public void initData() {
    ObjectMapper objectMapper = new ObjectMapper();
    try (InputStream inputStream = getClass().getResourceAsStream("/electrolytes.json")) {
      if (inputStream == null) {
        throw new IOException("Electrolytes JSON file not found.");
      }

      Map<String, List<Electrolyte>> map = objectMapper.readValue(inputStream, new TypeReference<>() {});
      List<Electrolyte> electrolytes = map.get("electrolytes");

      for (Electrolyte electrolyte : electrolytes) {
        electrolyteEntityMap.put(electrolyte.getName(), electrolyte);
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to initialize electrolyte data", e);
    }
  }
}