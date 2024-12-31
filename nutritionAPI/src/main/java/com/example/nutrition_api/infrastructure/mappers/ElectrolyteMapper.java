package com.example.nutrition_api.infrastructure.mappers;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PairMapper.class})
public interface ElectrolyteMapper {

  ElectrolyteView toView(Electrolyte entity);
}
