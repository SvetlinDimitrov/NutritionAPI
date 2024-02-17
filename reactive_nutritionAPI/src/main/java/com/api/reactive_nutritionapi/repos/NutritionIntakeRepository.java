package com.api.reactive_nutritionapi.repos;

import com.api.reactive_nutritionapi.domain.entity.NutritionIntakeEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NutritionIntakeRepository extends R2dbcRepository<NutritionIntakeEntity, Long> {
  @Query("select n.* from nutrition_intakes n where n.record_id = :recordId && n.nutrition_name = :name")
  Mono<NutritionIntakeEntity> findByNutrientNameAndRecordId(@Param("recordId") Long recordId,
                                                            @Param("name") String name);

  Flux<NutritionIntakeEntity> findAllByRecordId(Long recordId);
}
