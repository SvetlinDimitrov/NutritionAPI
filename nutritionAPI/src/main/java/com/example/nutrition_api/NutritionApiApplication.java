package com.example.nutrition_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NutritionApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(NutritionApiApplication.class, args);
  }
}
