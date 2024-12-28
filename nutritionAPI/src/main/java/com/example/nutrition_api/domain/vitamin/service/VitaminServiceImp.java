package com.example.nutrition_api.domain.vitamin.service;

import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import com.example.nutrition_api.domain.vitamin.entity.Vitamin;
import com.example.nutrition_api.infrastructure.exceptions.VitaminNotFoundException;
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
public class VitaminServiceImp implements VitaminService {

  private final Map<String, Vitamin> vitaminMap = new LinkedHashMap<>();
  private final ViewConverter converter;

  public List<VitaminView> getAll() {
    return vitaminMap
        .values()
        .stream()
        .map(converter::toView).toList();
  }

  public VitaminView getByName(String name) throws VitaminNotFoundException {

    if (vitaminMap.containsKey(name)) {
      return converter.toView(vitaminMap.get(name));
    }
    throw new VitaminNotFoundException(name);

  }

  public String getAllVitaminsNames() {
    return String.join(",", vitaminMap.keySet());
  }

  public List<Vitamin> findAll() {
    return vitaminMap.values().stream().toList();
  }

  @PostConstruct
  public void initData() {
    ObjectMapper objectMapper = new ObjectMapper();
    try (InputStream inputStream = getClass().getResourceAsStream("/vitamins.json")) {
      if (inputStream == null) {
        throw new IOException("Vitamins JSON file not found.");
      }

      Map<String, List<Vitamin>> map = objectMapper.readValue(inputStream, new TypeReference<>() {});
      List<Vitamin> vitamins = map.get("vitamins");

      for (Vitamin vitamin : vitamins) {
        vitaminMap.put(vitamin.getName(), vitamin);
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to initialize vitamin data", e);
    }
  }
}

