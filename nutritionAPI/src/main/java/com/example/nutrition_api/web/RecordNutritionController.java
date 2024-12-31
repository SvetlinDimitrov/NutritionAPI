package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.record.dto.NutrientUpdateRequest;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.service.RecordService;
import com.example.nutrition_api.infrastructure.open_ai.RecordNutritionControllerDocs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordNutritionController implements RecordNutritionControllerDocs {

  private final RecordService recordService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasRole('COMPLETED')")
  public Page<RecordView> getAll(Pageable pageable) {
    return recordService.getAll(pageable);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAnyRole('COMPLETED') && @userPermissionEvaluator.recordUserOwner(authentication, #id)")
  public RecordView getById(@PathVariable Long id) {
    return recordService.getById(id);
  }

  @PatchMapping("/edit/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAnyRole('COMPLETED') && @userPermissionEvaluator.recordUserOwner(authentication, #id)")
  public NutritionIntakeView edit(@Valid @RequestBody NutrientUpdateRequest dto, @PathVariable Long id) {
    return recordService.updateById(id, dto);
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAnyRole('COMPLETED')")
  public RecordView create() {
    return recordService.addNewRecordByUserId();
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAnyRole('COMPLETED') && @userPermissionEvaluator.recordUserOwner(authentication, #id)")
  public void delete(@PathVariable Long id) {
    recordService.deleteById(id);
  }

}
