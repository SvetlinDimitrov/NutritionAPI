package com.example.nutrition_api.seed;

import com.example.nutrition_api.domain.vitamin.entity.Vitamin;
import com.example.nutrition_api.domain.vitamin.repository.VitaminRepository;
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
public class VitaminSeeder implements CommandLineRunner {

  private final VitaminRepository vitaminRepository;

  @Override
  public void run(String... args) {
    initData();
  }

  private void initData() {
    ObjectMapper objectMapper = new ObjectMapper();
    try (InputStream inputStream = getClass().getResourceAsStream("/vitamins.json")) {
      if (inputStream == null) {
        throw new IOException("Vitamins JSON file not found.");
      }

      Map<String, List<Vitamin>> map = objectMapper.readValue(inputStream, new TypeReference<>() {});
      List<Vitamin> vitamins = map.get("vitamins");

      for (Vitamin vitamin : vitamins) {
        vitaminRepository.save(vitamin);
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to initialize vitamin data", e);
    }
  }
}