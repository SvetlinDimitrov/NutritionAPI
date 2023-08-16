package com.example.nutritionapi.repos;

import com.example.nutritionapi.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitaminRepository extends JpaRepository<VitaminEntity , Long> {
}

