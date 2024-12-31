package com.example.nutrition_api.infrastructure.security.config;

import com.example.nutrition_api.domain.record.service.RecordService;
import com.example.nutrition_api.infrastructure.security.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component(value = "userPermissionEvaluator")
@RequiredArgsConstructor
public class UserPermissionEvaluator {

  private final RecordService recordService;

  public boolean recordUserOwner(Authentication authentication, Long id) {
    if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {

      return recordService.existsByRecordIdAndUserEmail(id, userDetails.user()
          .getEmail());
    }
    return false;
  }
}
