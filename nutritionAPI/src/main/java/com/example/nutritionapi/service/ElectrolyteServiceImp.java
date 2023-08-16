package com.example.nutritionapi.service;

import com.example.nutritionapi.repos.ElectrolyteRepository;
import org.springframework.stereotype.Service;

@Service
public class ElectrolyteServiceImp {
    private final ElectrolyteRepository electrolyteRepository;

    public ElectrolyteServiceImp(ElectrolyteRepository electrolyteRepository) {
        this.electrolyteRepository = electrolyteRepository;
    }
}
