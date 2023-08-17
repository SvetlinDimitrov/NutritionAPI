package com.example.nutritionapi.repos;

import com.example.nutritionapi.domain.entity.ElectrolyteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElectrolyteRepository extends JpaRepository<ElectrolyteEntity, Long> {
    Optional<ElectrolyteEntity> findByName(String name);
}
