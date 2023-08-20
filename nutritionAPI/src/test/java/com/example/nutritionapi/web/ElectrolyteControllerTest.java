package com.example.nutritionapi.web;

import com.example.nutritionapi.service.ElectrolyteServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ElectrolyteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ElectrolyteServiceImp electrolyteService;



    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllElectrolytes_fiveElectrolytesExpected_successful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/electrolyte"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Sodium")))
                .andExpect(jsonPath("$[1].name", is("Potassium")))
                .andExpect(jsonPath("$[2].name", is("Calcium")))
                .andExpect(jsonPath("$[3].name", is("Magnesium")))
                .andExpect(jsonPath("$[4].name", is("Chloride")));
    }

    @Test
    void getElectrolyteByName_validName_successful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/electrolyte/"+"Sodium"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Sodium")))
                .andExpect(jsonPath("$.functions[0].key", is("Fluid Balance")))
                .andExpect(jsonPath("$.sources[0].key", is("Table salt")))
                .andExpect(jsonPath("$.healthConsiderations[0].key", is("High Blood Pressure")))
                .andExpect(jsonPath("$.maleLowerBoundIntake", is(2300)))
                .andExpect(jsonPath("$.maleHigherBoundIntake", is(2300)))
                .andExpect(jsonPath("$.femaleLowerBoundIntake", is(2300)))
                .andExpect(jsonPath("$.femaleHigherBoundIntake", is(2300)))
                .andExpect(jsonPath("$.measure", is("milligrams (mg)")));

    }

    @Test
    void getElectrolyteByName_invalidName_notFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/electrolyte/"+"invalidName"))
                .andExpect(status().isNotFound());

    }
}