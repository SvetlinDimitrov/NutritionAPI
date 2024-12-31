package com.example.nutrition_api.domain.record.repository;

import com.example.nutrition_api.domain.record.entity.Record;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

  Page<Record> findAllByUser_Email(String email, Pageable pageable);

  @Query("SELECT r FROM Record r WHERE r.id = :recordId AND r.user.email = :userEmail")
  Optional<Record> findByIdAndUser_Email(@Param("recordId") Long recordId, @Param("userEmail") String userEmail);

  boolean existsByUser_Email(String userEmail);

  boolean existsByIdAndUser_Email(Long recordId, String userEmail);
}