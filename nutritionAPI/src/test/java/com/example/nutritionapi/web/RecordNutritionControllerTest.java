//package com.example.nutritionapi.web;
//
//import com.example.nutritionapi.domain.constants.enums.Gender;
//import com.example.nutritionapi.domain.constants.enums.WorkoutState;
//import com.example.nutritionapi.domain.dtos.NutrientChangeDto;
//import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
//import com.example.nutritionapi.domain.dtos.viewDtos.NutritionIntakeView;
//import com.example.nutritionapi.domain.entity.UserEntity;
//import com.example.nutritionapi.repos.RecordRepository;
//import com.example.nutritionapi.repos.UserRepository;
//import com.example.nutritionapi.service.RecordServiceImp;
//import com.example.nutritionapi.service.UserServiceImp;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//class RecordNutritionControllerTest {
//
//    @Autowired
//    private RecordServiceImp recordService;
//    @Autowired
//    private UserServiceImp userServiceImp;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RecordRepository recordRepository;
//    @Autowired
//    private MockMvc mockMvc;
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        objectMapper = new ObjectMapper();
//        if (userRepository.count() == 0) {
//            registerUserWithNotCompleteUserDetails();
//            registerUserWithCompleteUserDetails();
//        }
//    }
//
//    @Test
//    void getAllRecords_notAuthorizeUser_forbidden() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/records")
//                        .contentType("application/json"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "notCompleteUser@abv.bg", authorities = {"ROLE_NOT_COMPLETED"})
//    void getAllRecords_authorizeUserNotCompleteUserDetails_forbidden() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/records")
//                        .contentType("application/json"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void getAllRecords_authorizeUserCompleteUserDetails_successful() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/records")
//                        .contentType("application/json"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getById_notAuthorizeUser_forbidden() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/records/1")
//                        .contentType("application/json"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "notCompleteUser@abv.bg", authorities = {"ROLE_NOT_COMPLETED"})
//    void getById_authorizeUserNotCompleteUserDetails_forbidden() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/records/" + 1)
//                        .contentType("application/json"))
//                .andExpect(status().isForbidden());
//
//    }
//
//    @Test
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void getById_authorizeUserCompleteUserDetails_successful() throws Exception {
//
//        UserEntity user = userServiceImp.findByEmail("completeUser@abv.bg");
//        Long VALID_ID = user.getRecords().get(0).getId();
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/records/" + VALID_ID)
//                        .contentType("application/json"))
//
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.day", Matchers.is(VALID_ID.intValue())))
//                .andExpect(jsonPath("$").value(Matchers.hasKey("dailyIntakeViews")))
//                .andExpect(jsonPath("$.userID", Matchers.is(user.getId().intValue())))
//                .andExpect(jsonPath("$.userName", Matchers.is(user.getUsername())));
//
//    }
//
//    @Test
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void getById_authorizeUserCompleteUserDetailsNotValidId_badRequest() throws Exception {
//
//        long INVALID_ID = getRoleId() + 1;
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/records/" + INVALID_ID)
//                        .contentType("application/json"))
//                .andExpect(status().isBadRequest());
//
//    }
//
//    @Test
//    void changeNutrientByRecordDay_notAuthorizeUser_forbidden() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.patch("/nutritionApi/records/edit/1")
//                        .contentType("application/json"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "notCompleteUser@abv.bg", authorities = {"ROLE_NOT_COMPLETED"})
//    void changeNutrientByRecordDay_AuthorizeUserNotCompleteUserDetails_forbidden() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.patch("/nutritionApi/records/edit/" + 1)
//                        .contentType("application/json"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void changeNutrientByRecordDay_authorizeUserCompleteUserDetailsInvalidId_badRequest() throws Exception {
//
//        long invalidRecordId = getRoleId() + 1;
//
//        mockMvc.perform(MockMvcRequestBuilders.patch("/nutritionApi/records/edit/" + invalidRecordId)
//                        .contentType("application/json"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void changeNutrientByRecordDay_authorizeUserCompleteUserDetailsValidIdInvalidNutritionChange_badRequest() throws Exception {
//
//        long recordId = getRoleId();
//
//        NutrientChangeDto nutrientChange = new NutrientChangeDto("invalidNutritionName" , new BigDecimal(100));
//
//        mockMvc.perform(MockMvcRequestBuilders.patch("/nutritionApi/records/edit/" + recordId)
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(nutrientChange)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void changeNutrientByRecordDay_authorizeUserCompleteUserDetailsValidIdValidNutritionChange_successful() throws Exception {
//
//        long recordId = getRoleId();
//
//        NutrientChangeDto nutrientChange = new NutrientChangeDto("Protein" , new BigDecimal(100));
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/nutritionApi/records/edit/" + recordId)
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(nutrientChange)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        String content = result.getResponse().getContentAsString();
//
//        NutritionIntakeView nutrition = objectMapper.readValue(content , NutritionIntakeView.class);
//
//        assertEquals(new BigDecimal("100.00"), nutrition.getDailyConsumed());
//    }
//
//    @Test
//    void createNewRecord_notAuthorizeUser_forbidden() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/records/endDay"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "notCompleteUser@abv.bg", authorities = {"ROLE_NOT_COMPLETED"})
//    void createNewRecord_authorizeUserNotCompleteUserDetails_forbidden() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/records/endDay"))
//                .andExpect(status().isForbidden());
//
//
//    }
//
//    @Test
//    @Transactional
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void createNewRecord_authorizeUserCompleteUserDetails_created() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/records"))
//                .andExpect(status().isCreated());
//
//        UserEntity user = userServiceImp.findByEmail("completeUser@abv.bg");
//        assertEquals(2 , user.getRecords().size());
//    }
//
//    @Test
//    void deleteRecord_notAuthorizeUser_forbidden() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/nutritionApi/records/delete/1"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "notCompleteUser@abv.bg", authorities = {"ROLE_NOT_COMPLETED"})
//    void deleteRecord_authorizeUserNotCompleteUserDetails_forbidden() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/nutritionApi/records/delete/1"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void deleteRecord_authorizeUserCompleteUserDetailsInvalidId_badRequest() throws Exception {
//
//        UserEntity user = userServiceImp.findByEmail("completeUser@abv.bg");
//        int invalidId = user.getRecords().size() + 1;
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/nutritionApi/records/"+invalidId))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @Transactional
//    @WithMockUser(username = "completeUser@abv.bg", authorities = {"ROLE_COMPLETED"})
//    void deleteRecord_authorizeUserCompleteUserDetailsValidId_successful() throws Exception {
//
//        UserEntity user = userServiceImp.findByEmail("completeUser@abv.bg");
//        int validId = user.getRecords().size();
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/nutritionApi/records/"+validId))
//                .andExpect(status().isNoContent());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/records/" +validId))
//                .andExpect(status().isBadRequest());
//
//    }
//
//    private void registerUserWithNotCompleteUserDetails() {
//        userServiceImp.register(
//                new RegisterUserDto()
//                        .setEmail("notCompleteUser@abv.bg")
//                        .setAge(23)
//                        .setPassword("valid")
//                        .setUsername("valid"));
//    }
//
//    private void registerUserWithCompleteUserDetails() {
//        userServiceImp.register(
//                new RegisterUserDto()
//                        .setEmail("completeUser@abv.bg")
//                        .setPassword("valid2")
//                        .setUsername("valid2")
//                        .setAge(18)
//                        .setGender(Gender.MALE)
//                        .setHeight(new BigDecimal(195))
//                        .setKg(new BigDecimal(85))
//                        .setWorkoutState(WorkoutState.VERY_ACTIVE));
//    }
//
//    private Long getRoleId() {
//        return userServiceImp.findByEmail("completeUser@abv.bg").getRecords().get(0).getId();
//    }
//}