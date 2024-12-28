package com.example.nutrition_api.infrastructure.open_ai;

import com.example.nutrition_api.infrastructure.security.dto.AuthenticationRequest;
import com.example.nutrition_api.infrastructure.security.dto.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;

@Tag(name = "Authentication Controller", description = "Controller for handling authentication operations")
public interface AuthenticationControllerDocs {

  @Operation(summary = "Create authentication token", description = "Authenticate user and generate a JWT token")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Authentication successful", content = @Content(
          mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
  })
  @RequestBody(
      content = @Content(
          mediaType = "application/json",
          examples = @ExampleObject(
              name = "AuthenticationRequestExample",
              summary = "Example of AuthenticationRequest",
              value = "{ \"email\": \"john.doe@example.com\", \"password\": \"password123\" }"
          )
      )
  )
  AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest);

  @Operation(summary = "Refresh authentication token", description = "Refresh the JWT token using the provided token")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Token refreshed successfully", content = @Content(
          mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
  })
  AuthenticationResponse refreshToken(UUID token);

}