package com.example.nutrition_api.infrastructure.security.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security")
public record EnvVariables(String apiSecretKey) {

}
