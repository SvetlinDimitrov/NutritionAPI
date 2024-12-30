package com.example.nutrition_api.domain.macros.service;

import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.domain.macros.entity.Macronutrient;
import java.util.List;

public interface MacronutrientService {

  List<Macronutrient> findAll();

  List<MacronutrientView> getAll();

  MacronutrientView getByName(String name);
}
