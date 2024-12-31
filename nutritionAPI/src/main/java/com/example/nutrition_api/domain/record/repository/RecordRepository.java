package com.example.nutrition_api.domain.record.repository;

import com.example.nutrition_api.domain.record.entity.Record;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

  @Query("SELECT r FROM Record r WHERE r.user.email = :email")
  List<Record> findAllByUser_Email(@Param("email") String email);

  @Query("SELECT r FROM Record r WHERE r.id = :recordId AND r.user.email = :userEmail")
  Optional<Record> findByIdAndUser_Email(@Param("recordId") Long recordId, @Param("userEmail") String userEmail);

  boolean existsByUser_Email(String userEmail);
}