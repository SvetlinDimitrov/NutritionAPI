package com.example.nutrition_api.domain.record.repository;

import com.example.nutrition_api.domain.record.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
