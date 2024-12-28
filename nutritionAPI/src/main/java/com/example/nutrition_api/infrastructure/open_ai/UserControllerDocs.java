package com.example.nutrition_api.infrastructure.open_ai;

import com.example.nutrition_api.domain.users.dto.EditUserDto;
import com.example.nutrition_api.domain.users.dto.LoginUserDto;
import com.example.nutrition_api.domain.users.dto.RegisterUserDto;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.infrastructure.exceptions.WrongUserCredentialsException;
import com.example.nutrition_api.infrastructure.security.dto.JWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User Controller", description = "Operations related to user management")
public interface UserControllerDocs {

  @Operation(summary = "Register user", description = "Register a new user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User registered"),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
  })
  void create(@Valid @RequestBody RegisterUserDto userDto,
      BindingResult result) throws WrongUserCredentialsException;

  @Operation(summary = "Login user", description = "Login a user and return a JWT token")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User logged in",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = JWT.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
  })
  JWT login(@Valid @RequestBody LoginUserDto userDto,
      BindingResult result) throws WrongUserCredentialsException;

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
  UserView edit(Principal principal, @RequestBody EditUserDto userDto);
}