package com.example.nutrition_api.infrastructure.open_ai;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI defineOpenApi() {
    var server = new Server()
        .url("http://localhost:8080/")
        .description("Development");

    var info = new Info()
        .title("Nutrition API")
        .version("v1.0")
        .description("Nutrition API is a platform designed to facilitate the operational "
            + "tasks in the execution process of the nutrition program. "
            + "The system supports functionalities for keeping track of nutrition "
            + "progress and feedback, absence tracking, and event management.");

    var components = new io.swagger.v3.oas.models.Components()
        .addSecuritySchemes("bearerAuth", new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT"));

    return new OpenAPI()
        .info(info)
        .servers(List.of(server))
        .components(components);
  }
}