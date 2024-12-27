package com.example.nutrition_api;

import com.example.nutrition_api.infrastructure.security.utils.EnvVariables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(EnvVariables.class)
public class NutritionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritionApiApplication.class, args);
	}

}
