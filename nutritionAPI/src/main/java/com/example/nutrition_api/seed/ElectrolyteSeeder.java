package com.example.nutrition_api.seed;

import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import com.example.nutrition_api.domain.electrolyte.repository.ElectrolyteRepository;
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
public class ElectrolyteSeeder implements CommandLineRunner {

  private final ElectrolyteRepository electrolyteRepository;

  @Override
  public void run(String... args) {
    initData();
  }

  private void initData() {
    ObjectMapper objectMapper = new ObjectMapper();
    try (InputStream inputStream = getClass().getResourceAsStream("/electrolytes.json")) {
      if (inputStream == null) {
        throw new IOException("Electrolytes JSON file not found.");
      }

      Map<String, List<Electrolyte>> map = objectMapper.readValue(inputStream, new TypeReference<>() {});
      List<Electrolyte> electrolytes = map.get("electrolytes");

      for (Electrolyte electrolyte : electrolytes) {
        electrolyteRepository.save(electrolyte);
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to initialize electrolyte data", e);
    }
  }
}