package com.example.nutrition_api.infrastructure.open_ai;

import com.example.nutrition_api.domain.users.dto.UserCreateRequest;
import com.example.nutrition_api.domain.users.dto.UserUpdateRequest;
import com.example.nutrition_api.domain.users.dto.UserView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User Controller", description = "Operations related to user management")
public interface UserControllerDocs {

  @Operation(summary = "Register user", description = "Register a new user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User registered"),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
  })
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
          mediaType = "application/json",
          examples = @ExampleObject(
              name = "RegisterUserDtoExample",
              summary = "Example of RegisterUserDto",
              value = "{ \"username\": \"john_doe\", \"email\": \"john.doe@example.com\", \"password\": \"password123\", \"kg\": 70, \"workoutState\": \"MODERATELY_ACTIVE\", \"gender\": \"MALE\", \"height\": 175, \"age\": 30 }"
          )
      )
  )
  void create(@Valid @RequestBody UserCreateRequest userDto);

  @Operation(summary = "Get user details", description = "Retrieve the details of the logged-in user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the user",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = UserView.class))}),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  @SecurityRequirement(name = "bearerAuth")
  UserView get(Principal principal);

  @Operation(summary = "Edit user details", description = "Edit the details of the logged-in user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "202", description = "User details updated",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = UserView.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  @SecurityRequirement(name = "bearerAuth")
  UserView edit(Principal principal, @RequestBody UserUpdateRequest userDto);
}