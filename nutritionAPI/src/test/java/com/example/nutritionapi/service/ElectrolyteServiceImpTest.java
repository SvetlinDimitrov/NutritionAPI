package com.example.nutritionapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ElectrolyteServiceImpTest {

    @Mock
    private  ElectrolyteRepository electrolyteRepository;

    @InjectMocks
    private ElectrolyteServiceImp electrolyteServiceImp;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllViewElectrolytes() {
    }

    @Test
    void getElectrolyteViewByName() {
    }

    @Test
    void getAllElectrolytesNames() {
    }
}