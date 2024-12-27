package com.example.nutrition_api.domain.users.repository;

import com.example.nutrition_api.domain.users.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query("SELECT e FROM UserEntity e LEFT JOIN FETCH e.records WHERE e.email = :email")
  Optional<UserEntity> findByEmail(String email);
}
