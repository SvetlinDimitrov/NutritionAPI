package com.example.nutrition_api.infrastructure.open_ai;

import com.example.nutrition_api.domain.record.dto.NutrientUpdateRequest;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Record Nutrition Controller", description = "Operations related to nutrition records")
@SecurityRequirement(name = "bearerAuth")
public interface RecordNutritionControllerDocs {

  @Operation(summary = "Get all records", description = "Retrieve a list of all nutrition records for the logged-in user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the records",
          content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))}),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  Page<RecordView> getAll(Pageable pageable);

  @Operation(summary = "Get record by id", description = "Retrieve a nutrition record by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the record",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RecordView.class))}),
      @ApiResponse(responseCode = "404", description = "Record not found", content = @Content),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  RecordView getById(Long id);

  @Operation(summary = "Edit record", description = "Edit a nutrition record for a specific id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Record edited",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = NutritionIntakeView.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "404", description = "Record not found", content = @Content),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  NutritionIntakeView edit(
      @Valid @RequestBody NutrientUpdateRequest dto,
      @PathVariable Long id);

  @Operation(summary = "Create record", description = "Create a new nutrition record for the logged-in user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Record created",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RecordView.class))}),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  RecordView create();

  @Operation(summary = "Delete record", description = "Delete a nutrition record by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Record deleted"),
      @ApiResponse(responseCode = "404", description = "Record not found", content = @Content),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  void delete(Long id);
}