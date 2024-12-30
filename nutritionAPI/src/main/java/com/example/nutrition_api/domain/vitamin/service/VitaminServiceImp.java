package com.example.nutrition_api.domain.vitamin.service;

import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.VITAMIN_NOT_FOUND;

import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import com.example.nutrition_api.domain.vitamin.entity.Vitamin;
import com.example.nutrition_api.infrastructure.exceptions.throwable.NotFoundException;
import com.example.nutrition_api.infrastructure.mappers.ViewConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

  public VitaminView getByName(String name) {
    return Optional.ofNullable(vitaminMap.get(name))
        .map(converter::toView)
        .orElseThrow(() -> new NotFoundException(VITAMIN_NOT_FOUND, String.join(",", vitaminMap.keySet())));
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

