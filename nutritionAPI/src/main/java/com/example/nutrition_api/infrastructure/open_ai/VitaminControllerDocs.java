package com.example.nutrition_api.infrastructure.open_ai;

import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Vitamin Controller", description = "Operations related to vitamins")
public interface VitaminControllerDocs {

  @Operation(summary = "Get all vitamins", description = "Retrieve a list of all vitamins")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the vitamins",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = VitaminView[].class))}),
  })
  List<VitaminView> getAll();

  @Operation(summary = "Get vitamin by name", description = "Retrieve a vitamin by its name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the vitamin",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = VitaminView.class))}),
      @ApiResponse(responseCode = "404", description = "Vitamin not found",
          content = @Content)
  })
  VitaminView getByName(String name);

}