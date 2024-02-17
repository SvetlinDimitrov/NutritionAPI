package com.api.reactive_nutritionapi.domain.constants.enums;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security")
public record EnvVariables(String apiSecretKey) {

}
