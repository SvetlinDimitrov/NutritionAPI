package com.example.nutrition_api.infrastructure.open_ai;

import com.example.nutrition_api.domain.record.dto.NutrientChangeDto;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.infrastructure.exceptions.IncorrectNutrientChangeException;
import com.example.nutrition_api.infrastructure.exceptions.RecordNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Record Nutrition Controller", description = "Operations related to nutrition records")
@SecurityRequirement(name = "bearerAuth")
public interface RecordNutritionControllerDocs {

  @Operation(summary = "Get all records", description = "Retrieve a list of all nutrition records for the logged-in user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the records",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RecordView[].class))}),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  List<RecordView> getAll(Principal principal);

  @Operation(summary = "Get record by day", description = "Retrieve a nutrition record by its day")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the record",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RecordView.class))}),
      @ApiResponse(responseCode = "404", description = "Record not found", content = @Content),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  RecordView getById(@PathVariable Long day) throws RecordNotFoundException;

  @Operation(summary = "Edit record", description = "Edit a nutrition record for a specific day")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Record edited",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = NutritionIntakeView.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "404", description = "Record not found", content = @Content),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  NutritionIntakeView edit(
      @Valid @RequestBody NutrientChangeDto dto,
      BindingResult result,
      @PathVariable Long day,
      Principal principal
  ) throws IncorrectNutrientChangeException, RecordNotFoundException;

  @Operation(summary = "Create record", description = "Create a new nutrition record for the logged-in user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Record created",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RecordView.class))}),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  RecordView create(Principal principal);

  @Operation(summary = "Delete record", description = "Delete a nutrition record by its day")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Record deleted"),
      @ApiResponse(responseCode = "404", description = "Record not found", content = @Content),
      @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content)
  })
  void delete(@PathVariable Long day, Principal principal) throws RecordNotFoundException;
}