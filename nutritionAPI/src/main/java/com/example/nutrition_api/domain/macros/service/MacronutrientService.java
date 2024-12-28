package com.example.nutrition_api.domain.macros.service;

import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.domain.macros.entity.Macronutrient;
import com.example.nutrition_api.infrastructure.exceptions.MacronutrientNotFoundException;
import java.util.List;

public interface MacronutrientService {

  List<Macronutrient> findAll();

  List<MacronutrientView> getAll();

  MacronutrientView getByName(String name) throws MacronutrientNotFoundException;

  String getAllMacrosNames();
}
