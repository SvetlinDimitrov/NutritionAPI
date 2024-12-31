package com.example.nutrition_api.seed;

import com.example.nutrition_api.domain.macros.entity.Macronutrient;
import com.example.nutrition_api.domain.macros.repository.MacronutrientRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MacronutrientSeeder implements CommandLineRunner {

  private final MacronutrientRepository macronutrientRepository;

  @Override
  public void run(String... args) {
    initData();
  }

  private void initData() {
    ObjectMapper objectMapper = new ObjectMapper();
    try (InputStream inputStream = getClass().getResourceAsStream("/macronutrients.json")) {
      if (inputStream == null) {
        throw new IOException("Macronutrient JSON file not found.");
      }

      Map<String, List<Macronutrient>> map = objectMapper.readValue(inputStream, new TypeReference<>() {});
      List<Macronutrient> macronutrients = map.get("macronutrients");

      for (Macronutrient macronutrient : macronutrients) {
        macronutrientRepository.save(macronutrient);
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to initialize macronutrient data", e);
    }
  }
}