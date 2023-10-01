package com.example.nutritionapi;

import com.example.nutritionapi.domain.constants.enums.EnvVariables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
