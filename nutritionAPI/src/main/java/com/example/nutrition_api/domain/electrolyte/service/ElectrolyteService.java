package com.example.nutrition_api.domain.electrolyte.service;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import java.util.List;

public interface ElectrolyteService {

  List<ElectrolyteView> getAll();

  ElectrolyteView getByName(String name);

  List<Electrolyte> findAll();
}
