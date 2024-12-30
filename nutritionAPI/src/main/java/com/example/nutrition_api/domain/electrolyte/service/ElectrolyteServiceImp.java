package com.example.nutrition_api.domain.electrolyte.service;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import com.example.nutrition_api.domain.electrolyte.repository.ElectrolyteRepository;
import com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages;
import com.example.nutrition_api.infrastructure.exceptions.throwable.NotFoundException;
import com.example.nutrition_api.infrastructure.mappers.ElectrolyteMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectrolyteServiceImp implements ElectrolyteService {

  private final ElectrolyteRepository repository;
  private final ElectrolyteMapper mapper;

  public List<Electrolyte> findAll() {
    return repository.findAll();
  }

  public List<ElectrolyteView> getAll() {
    return repository.findAll()
        .stream()
        .map(mapper::toView)
        .toList();
  }

  public ElectrolyteView getByName(String name) {
    return repository.get(name)
        .map(mapper::toView)
        .orElseThrow(() -> new NotFoundException(ExceptionMessages.ELECTROLYTE_NOT_FOUND, String.join(",", repository.findAll()
            .stream()
            .map(Electrolyte::getName)
            .toList())));
  }
}