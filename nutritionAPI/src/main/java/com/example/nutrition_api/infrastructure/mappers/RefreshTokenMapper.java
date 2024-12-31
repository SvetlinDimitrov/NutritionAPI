package com.example.nutrition_api.infrastructure.mappers;

import com.example.nutrition_api.infrastructure.security.refresh_token.dto.RefreshTokenView;
import com.example.nutrition_api.infrastructure.security.refresh_token.entity.RefreshToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {

  @Mapping(target = "token", source = "id")
  @Mapping(target = "expirationTime", source = "expiryDate")
  RefreshTokenView toView(RefreshToken entity);
}
