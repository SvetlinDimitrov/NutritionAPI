package com.example.nutrition_api.domain.electrolyte.service;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import com.example.nutrition_api.infrastructure.exceptions.ElectrolyteNotFoundException;
import java.util.List;

public interface ElectrolyteService {

  List<ElectrolyteView> getAll();

  ElectrolyteView getByName(String name) throws ElectrolyteNotFoundException;

  List<Electrolyte> findAll();

  String getAllElectrolytesNames();
}
