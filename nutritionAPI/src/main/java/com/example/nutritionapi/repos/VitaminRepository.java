package com.example.nutritionapi.repos;

import com.example.nutritionapi.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VitaminRepository extends JpaRepository<VitaminEntity , Long> {
    Optional<VitaminEntity> findByName(String name);
}

