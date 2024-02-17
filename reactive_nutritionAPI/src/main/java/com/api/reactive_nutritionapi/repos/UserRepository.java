package com.api.reactive_nutritionapi.repos;

import com.api.reactive_nutritionapi.domain.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {
  Mono<UserEntity> findByEmail(String email);
}
