package com.example.nutrition_api.domain.vitamin.service;

import static com.example.nutrition_api.infrastructure.exceptions.ExceptionMessages.VITAMIN_NOT_FOUND;

import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import com.example.nutrition_api.domain.vitamin.entity.Vitamin;
import com.example.nutrition_api.domain.vitamin.repository.VitaminRepository;
import com.example.nutrition_api.infrastructure.exceptions.throwable.NotFoundException;
import com.example.nutrition_api.infrastructure.mappers.VitaminMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VitaminServiceImp implements VitaminService {

  private final VitaminRepository vitaminRepository;
  private final VitaminMapper mapper;

  public List<VitaminView> getAll() {
    return vitaminRepository.findAll()
        .stream()
        .map(mapper::toView)
        .toList();
  }

  public VitaminView getByName(String name) {
    return vitaminRepository.get(name)
        .map(mapper::toView)
        .orElseThrow(() -> new NotFoundException(VITAMIN_NOT_FOUND, String.join(",", vitaminRepository.findAll()
            .stream()
            .map(Vitamin::getName)
            .toList())));
  }

  public List<Vitamin> findAll() {
    return vitaminRepository.findAll();
  }
}