package com.example.nutritionapi.repos;

import com.example.nutritionapi.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT e FROM UserEntity e LEFT JOIN FETCH e.records WHERE e.email = :email")
    Optional<UserEntity> findByEmail(String email);
}
