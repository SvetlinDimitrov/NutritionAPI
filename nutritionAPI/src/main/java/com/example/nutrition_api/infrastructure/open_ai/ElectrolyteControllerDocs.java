package com.example.nutrition_api.infrastructure.open_ai;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.infrastructure.exceptions.ElectrolyteNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Electrolyte Controller", description = "Operations related to electrolytes")
public interface ElectrolyteControllerDocs {

  @Operation(summary = "Get all electrolytes", description = "Retrieve a list of all electrolytes")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the electrolytes",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ElectrolyteView[].class))}),
  })
  List<ElectrolyteView> getAll();

  @Operation(summary = "Get electrolyte by name", description = "Retrieve an electrolyte by its name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the electrolyte",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ElectrolyteView.class))}),
      @ApiResponse(responseCode = "404", description = "Electrolyte not found",
          content = @Content)
  })
  ElectrolyteView getByName(@PathVariable String name)
      throws ElectrolyteNotFoundException;
}
