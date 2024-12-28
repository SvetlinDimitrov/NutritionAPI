package com.example.nutrition_api.domain.vitamin.service;

import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import com.example.nutrition_api.domain.vitamin.entity.Vitamin;
import com.example.nutrition_api.infrastructure.exceptions.VitaminNotFoundException;
import java.util.List;

public interface VitaminService {

  List<VitaminView> getAll();

  VitaminView getByName(String name) throws VitaminNotFoundException;

  String getAllVitaminsNames();

  List<Vitamin> findAll();
}
