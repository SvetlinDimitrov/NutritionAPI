package com.example.nutrition_api.infrastructure.mappers;

import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import com.example.nutrition_api.domain.vitamin.entity.Vitamin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PairMapper.class})
public interface VitaminMapper {

  VitaminView toView(Vitamin entity);
}
