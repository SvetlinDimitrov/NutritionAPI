package com.example.nutrition_api.infrastructure.security.refresh_token.repository;

import com.example.nutrition_api.infrastructure.security.refresh_token.entity.RefreshToken;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

}
