package com.example.nutrition_api.domain.record.repository;

import com.example.nutrition_api.domain.record.entity.NutritionIntake;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientIntakeRepository extends JpaRepository<NutritionIntake, Long> {

  @Query("SELECT ni FROM NutritionIntake ni WHERE ni.nutrientName = :name AND ni.record.id = :recordId")
  Optional<NutritionIntake> findByNameAndRecordId(@Param("name") String name, @Param("recordId") Long recordId);
}