//package com.example.nutritionapi.web;
//
//import com.example.nutritionapi.service.VitaminServiceImp;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//class VitaminControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private VitaminServiceImp electrolyteService;
//
//
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void getAllVitamins_thirteenVitaminsExpected_successful() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/vitamin"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is("A")))
//                .andExpect(jsonPath("$[1].name", is("D")))
//                .andExpect(jsonPath("$[2].name", is("E")))
//                .andExpect(jsonPath("$[3].name", is("K")))
//                .andExpect(jsonPath("$[4].name", is("C")))
//                .andExpect(jsonPath("$[5].name", is("B1")))
//                .andExpect(jsonPath("$[6].name", is("B2")))
//                .andExpect(jsonPath("$[7].name", is("B3")))
//                .andExpect(jsonPath("$[8].name", is("B5")))
//                .andExpect(jsonPath("$[9].name", is("B6")))
//                .andExpect(jsonPath("$[10].name", is("B7")))
//                .andExpect(jsonPath("$[11].name", is("B9")))
//                .andExpect(jsonPath("$[12].name", is("B12")));
//    }
//
//    @Test
//    void getVitaminByName_validName_successful() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/vitamin/"+"A"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("A")))
//                .andExpect(jsonPath("$.functions[0].key", is("Vision")))
//                .andExpect(jsonPath("$.sources[0].key", is("Preformed Vitamin A (Retinoids)")))
//                .andExpect(jsonPath("$.maleLowerBoundIntake", is(700)))
//                .andExpect(jsonPath("$.maleHigherBoundIntake", is(900)))
//                .andExpect(jsonPath("$.femaleLowerBoundIntake", is(600)))
//                .andExpect(jsonPath("$.femaleHigherBoundIntake", is(700)))
//                .andExpect(jsonPath("$.measure", is("micrograms of retinol activity equivalents (RAE)")));
//
//    }
//
//    @Test
//    void getVitaminByName_invalidName_notFound() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/vitamin/"+"invalidName"))
//                .andExpect(status().isBadRequest());
//
//    }
//}