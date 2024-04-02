package com.api.reactive_nutritionapi.web;

import com.api.reactive_nutritionapi.domain.dtos.NutrientChangeDto;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.NutritionIntakeView;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.RecordView;
import com.api.reactive_nutritionapi.exceptions.IncorrectNutrientChangeException;
import com.api.reactive_nutritionapi.exceptions.RecordNotFoundException;
import com.api.reactive_nutritionapi.service.RecordServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/nutritionApi/records")
public class RecordNutritionController {

  private final RecordServiceImp recordService;

  public RecordNutritionController(RecordServiceImp recordService) {
    this.recordService = recordService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<RecordView> getAllRecords(Mono<Principal> principal) {
    return principal.flatMapMany(user -> recordService.getAllViewsByUserId(user.getName()));

  }

  @GetMapping("/{recordId}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<RecordView> getById(Mono<Principal> principal, @PathVariable Mono<Long> recordId) {
    return principal
        .flatMap(user ->
            recordId.flatMap(id -> recordService.getViewByRecordIdAndUserId(id, user.getName()))
        );
  }

  @PatchMapping("/edit/{recordId}")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<NutritionIntakeView> changeNutrientByRecordDay(@RequestBody NutrientChangeDto dto,
                                                             @PathVariable Long recordId,
                                                             Principal principal) throws IncorrectNutrientChangeException, RecordNotFoundException {
    return recordService.updateRecordById(recordId, principal.getName(), dto);
  }


  @DeleteMapping("/{recordId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteRecord(@PathVariable Long recordId, Principal principal) throws RecordNotFoundException {
    return recordService.deleteById(recordId, principal.getName());
  }
}
