package com.example.nutrition_api.infrastructure.open_ai;

import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.infrastructure.exceptions.MacronutrientNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Macronutrient Controller", description = "Operations related to macronutrients")
public interface MacronutrientControllerDocs {

  @Operation(summary = "Get all macronutrients", description = "Retrieve a list of all macronutrients")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the macronutrients",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = MacronutrientView[].class))}),
  })
  List<MacronutrientView> getAll();

  @Operation(summary = "Get macronutrient by name", description = "Retrieve a macronutrient by its name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the macronutrient",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = MacronutrientView.class))}),
      @ApiResponse(responseCode = "404", description = "Macronutrient not found",
          content = @Content)
  })
  MacronutrientView getByName(@PathVariable String name)
      throws MacronutrientNotFoundException;
}