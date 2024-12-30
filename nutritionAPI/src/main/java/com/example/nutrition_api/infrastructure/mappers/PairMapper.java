package com.example.nutrition_api.infrastructure.mappers;

import com.example.nutrition_api.domain.shared.dto.PairView;
import com.example.nutrition_api.domain.shared.entity.Pair;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PairMapper {

  PairView toView(Pair entity);
}
