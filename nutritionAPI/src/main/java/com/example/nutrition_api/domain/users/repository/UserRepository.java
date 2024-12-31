package com.example.nutrition_api.domain.users.repository;

import com.example.nutrition_api.domain.users.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT e FROM User e LEFT JOIN FETCH e.records WHERE e.email = :email")
  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}
