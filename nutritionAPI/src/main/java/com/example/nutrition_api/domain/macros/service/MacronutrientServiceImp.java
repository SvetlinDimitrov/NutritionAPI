package com.example.nutrition_api.domain.macros.service;

import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.MACRONUTRIENT_NOT_FOUND;

import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.domain.macros.entity.Macronutrient;
import com.example.nutrition_api.domain.macros.repository.MacronutrientRepository;
import com.example.nutrition_api.infrastructure.exceptions.throwable.NotFoundException;
import com.example.nutrition_api.infrastructure.mappers.MacronutrientMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MacronutrientServiceImp implements MacronutrientService {

  private final MacronutrientRepository macronutrientRepository;
  private final MacronutrientMapper mapper;

  public List<Macronutrient> findAll() {
    return macronutrientRepository.findAll();
  }

  public List<MacronutrientView> getAll() {
    return macronutrientRepository.findAll()
        .stream()
        .map(mapper::toView)
        .toList();
  }

  public MacronutrientView getByName(String name) {
    return macronutrientRepository.get(name)
        .map(mapper::toView)
        .orElseThrow(() -> new NotFoundException(MACRONUTRIENT_NOT_FOUND, String.join(",", macronutrientRepository.findAll()
            .stream()
            .map(Macronutrient::getName)
            .toList())));
  }
}