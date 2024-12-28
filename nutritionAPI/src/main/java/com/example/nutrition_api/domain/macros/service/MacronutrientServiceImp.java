package com.example.nutrition_api.domain.macros.service;

import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.domain.macros.entity.Macronutrient;
import com.example.nutrition_api.infrastructure.exceptions.MacronutrientNotFoundException;
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
public class MacronutrientServiceImp implements MacronutrientService {

  private final Map<String, Macronutrient> macronutrientMap = new LinkedHashMap<>();
  private final ViewConverter converter;

  public List<Macronutrient> findAll() {
    return macronutrientMap.values().stream().toList();
  }

  public List<MacronutrientView> getAll() {
    return macronutrientMap
        .values()
        .stream()
        .map(converter::toView)
        .toList();
  }

  public MacronutrientView getByName(String name) throws MacronutrientNotFoundException {
    if (macronutrientMap.containsKey(name)) {
      return converter.toView(macronutrientMap.get(name));
    }
    throw new MacronutrientNotFoundException(name);
  }

  public String getAllMacrosNames() {
    return String.join(",", macronutrientMap.keySet());
  }

  @PostConstruct
  public void initData() {
    ObjectMapper objectMapper = new ObjectMapper();
    try (InputStream inputStream = getClass().getResourceAsStream("/macronutrients.json")) {
      if (inputStream == null) {
        throw new IOException("Macronutrient JSON file not found.");
      }

      Map<String, List<Macronutrient>> map = objectMapper.readValue(inputStream, new TypeReference<>() {});
      List<Macronutrient> macronutrients = map.get("macronutrients");

      for (Macronutrient macronutrient : macronutrients) {
        macronutrientMap.put(macronutrient.getName(), macronutrient);
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to initialize macronutrient data", e);
    }
  }
}