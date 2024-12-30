package com.example.nutrition_api.infrastructure.mappers.user;

import com.example.nutrition_api.domain.users.dto.UserCreateRequest;
import com.example.nutrition_api.domain.users.dto.UserUpdateRequest;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    uses = PasswordEncoderComponent.class,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

  UserView toView(User entity);

  @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
  @Mapping(target = "kilograms", source = "kg")
  User toEntity(UserCreateRequest dto);

  User updateEntity(@MappingTarget User entity, UserUpdateRequest dto);
}
