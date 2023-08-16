package com.example.nutritionapi.service;

import com.example.nutritionapi.repos.RecordRepository;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImp {
    private final RecordRepository recordRepository;

    public RecordServiceImp(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
}
