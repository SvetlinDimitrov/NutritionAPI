package com.example.nutritionapi.repos;

import com.example.nutritionapi.domain.entity.MacronutrientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MacronutrientRepository extends JpaRepository<MacronutrientEntity, Long> {
    Optional<MacronutrientEntity> findByName(String name);
}
