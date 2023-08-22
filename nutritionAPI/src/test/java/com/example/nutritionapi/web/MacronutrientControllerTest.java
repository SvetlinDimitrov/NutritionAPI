package com.example.nutritionapi.web;

import com.example.nutritionapi.service.ElectrolyteServiceImp;
import com.example.nutritionapi.service.MacronutrientServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
class MacronutrientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MacronutrientServiceImp macronutrientService;



    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllMacrosView_threeMacrosExpected_successful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/macronutrient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Carbohydrates")))
                .andExpect(jsonPath("$[1].name", is("Protein")))
                .andExpect(jsonPath("$[2].name", is("Fat")));

    }

    @Test
    void getMacroViewByName_validName_successful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/macronutrient/"+"Carbohydrates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Carbohydrates")))
                .andExpect(jsonPath("$.functions[0].key", is("Energy Source")))
                .andExpect(jsonPath("$.activeState", is(0.50)))
                .andExpect(jsonPath("$.inactiveState", is(0.50)))
                .andExpect(jsonPath("$.sources[0].key", is("Grains")))
                .andExpect(jsonPath("$.types[0].key", is("Simple Carbohydrates")))
                .andExpect(jsonPath("$.dietaryConsiderations[0].key", is("Fiber")));

    }

    @Test
    void getMacroViewByName_invalidName_notFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/macronutrient/"+"invalidName"))
                .andExpect(status().isBadRequest());

    }
}