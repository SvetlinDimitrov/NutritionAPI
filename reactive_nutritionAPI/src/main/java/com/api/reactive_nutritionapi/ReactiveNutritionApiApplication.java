package com.api.reactive_nutritionapi;

import com.api.reactive_nutritionapi.domain.constants.enums.EnvVariables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EnvVariables.class)
public class ReactiveNutritionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveNutritionApiApplication.class, args);
	}

}
