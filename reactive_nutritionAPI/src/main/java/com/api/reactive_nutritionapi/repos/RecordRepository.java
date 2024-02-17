package com.api.reactive_nutritionapi.repos;

import com.api.reactive_nutritionapi.domain.entity.RecordEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RecordRepository extends R2dbcRepository<RecordEntity, Long> {
  Mono<Boolean> deleteByIdAndUserId(Long id, Long userId);

  Flux<RecordEntity> findAllByUserId(Long userId);

  Mono<RecordEntity> findByIdAndUserId(Long id, Long userId);

  @Query("SELECT r.*, n.* FROM records r" +
      " LEFT JOIN nutrition_intakes n" +
      " ON r.id = n.record_id WHERE r.id = :recordId")
  Mono<RecordEntity> findById(@Param("recordId") Long recordId);
}
