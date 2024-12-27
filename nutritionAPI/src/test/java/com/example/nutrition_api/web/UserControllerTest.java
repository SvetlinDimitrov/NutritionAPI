//package com.example.nutritionapi.web;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
////import com.example.nutritionapi.config.security.JwtUtil;
////import com.example.nutritionapi.feature.users.enums.Gender;
////import com.example.nutritionapi.feature.users.enums.UserDetails;
////import com.example.nutritionapi.feature.users.enums.WorkoutState;
////import com.example.nutritionapi.feature.users.dto.EditUserDto;
////import com.example.nutritionapi.feature.users.dto.LoginUserDto;
////import com.example.nutritionapi.feature.users.dto.RegisterUserDto;
////import com.example.nutritionapi.feature.users.dto.UserView;
////import com.example.nutritionapi.feature.users.repository.UserRepository;
////import com.example.nutritionapi.feature.users.service.UserServiceImp;
////import com.fasterxml.jackson.databind.ObjectMapper;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.test.context.support.WithMockUser;
////import org.springframework.test.web.servlet.MockMvc;
////import org.springframework.test.web.servlet.MvcResult;
////import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
////import org.springframework.transaction.annotation.Transactional;
////
////import java.math.BigDecimal;
////
////import static org.junit.jupiter.api.Assertions.assertEquals;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//////TODO: when i run each test individually everything works , but when i run all them together in java folder some of these test explodes
//@AutoConfigureMockMvc
//@SpringBootTest
//class UserControllerTest {
////
////
////    @Autowired
////    private MockMvc mockMvc;
////    private ObjectMapper objectMapper;
////    @Autowired
////    private JwtUtil jwtUtil;
////    @Autowired
////    private UserServiceImp userServiceImp;
////    @Autowired
////    private UserRepository userRepository;
////
////
////    @BeforeEach
////    void setUp() {
////        objectMapper = new ObjectMapper();
////        if (userRepository.count() == 0) {
////            RegisterUserDto registerUser = createValidRegisterUser();
////            userServiceImp.register(registerUser);
////        }
////    }
////
////    @Test
////    @Transactional
////    void createUserAccount_validUserCreation_successful() throws Exception {
////        RegisterUserDto registerUser =
////                new RegisterUserDto()
////                        .setEmail("valid1@abv.bg")
////                        .setAge(23)
////                        .setPassword("valid1")
////                        .setUsername("valid1");
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
////                        .content(objectMapper.writeValueAsString(registerUser))
////                        .contentType("application/json"))
////                .andExpect(status().isCreated());
////    }
////
////    @Test
////    @Transactional
////    void createUserAccount_invalidUserCreation_badRequest() throws Exception {
////        RegisterUserDto registerUser = new RegisterUserDto();
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
////                        .content(objectMapper.writeValueAsString(registerUser))
////                        .contentType("application/json"))
////                .andExpect(status().isBadRequest());
////    }
////
////    @Test
////    @Transactional
////    void createUserAccount_usedEmail_badRequest() throws Exception {
////
////        RegisterUserDto validUserRegistration = createValidRegisterUser();
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
////                        .content(objectMapper.writeValueAsString(validUserRegistration))
////                        .contentType("application/json"))
////                .andExpect(status().isBadRequest());
////    }
////
////
////    @Test
////    @Transactional
////    void login_validUserCredential_successful() throws Exception {
////
////        LoginUserDto loginUserDto = loginUserDto();
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/login")
////                        .content(objectMapper.writeValueAsString(loginUserDto))
////                        .contentType("application/json"))
////                .andExpect(status().isOk());
////
////    }
////
////    @Test
////    @Transactional
////    void login_invalidUserCredential_badRequest() throws Exception {
////
////        LoginUserDto loginUserDto = new LoginUserDto();
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/login")
////                        .content(objectMapper.writeValueAsString(loginUserDto))
////                        .contentType("application/json"))
////                .andExpect(status().isBadRequest());
////
////    }
////
////    @Test
////    @Transactional
////    void login_wrongCredentials_badRequest() throws Exception {
////
////        LoginUserDto loginUserDto = new LoginUserDto()
////                .setPassword("testttt")
////                .setEmail("test@abv.bg");
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/login")
////                        .content(objectMapper.writeValueAsString(loginUserDto))
////                        .contentType("application/json"))
////                .andExpect(status().isBadRequest());
////
////    }
////
////    @Test
////    @Transactional
////    @WithMockUser(username = "valid@abv.bg", authorities = {"ROLE_NOT_COMPLETED"})
////    void getUserDetails_withAuthorizeUser_successful() throws Exception {
////
////        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/user/details"))
////                .andExpect(status().isOk());
////
////    }
////
////    @Test
////    @Transactional
////    void getUserDetails_withNotAuthorizeUser_Forbidden() throws Exception {
////
////        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/user/details"))
////                .andExpect(status().isForbidden());
////
////    }
////
////    @Test
////    @Transactional
////    void editUserProfile_withNotAuthorizeUser_Forbidden() throws Exception {
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/details/1"))
////                .andExpect(status().isForbidden());
////    }
////
////
////    @Test
////    @Transactional
////    @WithMockUser(username = "valid@abv.bg", authorities = {"ROLE_NOT_COMPLETED"})
////    void editUserProfile_withAuthorizeUser_successful() throws Exception {
////
////        EditUserDto editUserDto1 = new EditUserDto()
////                .setAge(56)
////                .setGender(Gender.MALE);
////
////        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/nutritionApi/user/details")
////                        .content(objectMapper.writeValueAsString(editUserDto1))
////                        .contentType("application/json"))
////                .andExpect(status().isAccepted())
////                .andReturn();
////
////        String content = result.getResponse().getContentAsString();
////
////        UserView userView = objectMapper.readValue(content, UserView.class);
////
////        assertEquals(UserDetails.NOT_COMPLETED, userView.getUserDetails());
////
////        EditUserDto editUserDto2 = new EditUserDto()
////                .setAge(56)
////                .setGender(Gender.MALE)
////                .setHeight(new BigDecimal(198))
////                .setKilograms(new BigDecimal(86))
////                .setWorkoutState(WorkoutState.MODERATELY_ACTIVE);
////
////        result = mockMvc.perform(MockMvcRequestBuilders.patch("/nutritionApi/user/details")
////                        .content(objectMapper.writeValueAsString(editUserDto2))
////                        .contentType("application/json"))
////                .andExpect(status().isAccepted())
////                .andReturn();
////
////        content = result.getResponse().getContentAsString();
////
////        userView = objectMapper.readValue(content, UserView.class);
////
////        assertEquals(UserDetails.COMPLETED, userView.getUserDetails());
////    }
////
////
////    private RegisterUserDto createValidRegisterUser() {
////        return new RegisterUserDto()
////                .setEmail("valid@abv.bg")
////                .setAge(23)
////                .setPassword("valid")
////                .setUsername("valid");
////    }
////
////    private LoginUserDto loginUserDto() {
////        return new LoginUserDto()
////                .setEmail("valid@abv.bg")
////                .setPassword("valid");
////    }
//}