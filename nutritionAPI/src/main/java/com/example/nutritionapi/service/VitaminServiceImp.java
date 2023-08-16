package com.example.nutritionapi.service;

import com.example.nutritionapi.repos.VitaminRepository;
import org.springframework.stereotype.Service;

@Service
public class VitaminServiceImp {
    private final VitaminRepository vitaminRepository;

    public VitaminServiceImp(VitaminRepository vitaminRepository) {
        this.vitaminRepository = vitaminRepository;
    }
}
