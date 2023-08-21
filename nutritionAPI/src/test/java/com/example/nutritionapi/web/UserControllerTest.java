package com.example.nutritionapi.web;

import com.example.nutritionapi.config.security.JwtUtil;
import com.example.nutritionapi.config.security.UserPrincipal;
import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;
import com.example.nutritionapi.domain.dtos.user.EditUserDto;
import com.example.nutritionapi.domain.dtos.user.LoginUserDto;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;

import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.repos.UserRepository;
import com.example.nutritionapi.service.UserServiceImp;

import com.example.nutritionapi.setUp.WithMockUserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private UserRepository repository;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @Transactional
    void createUserAccount_validUserCreation_successful() throws Exception {
        RegisterUserDto registerUser = createValidRegisterUser();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    void createUserAccount_invalidUserCreation_badRequest() throws Exception {
        RegisterUserDto registerUser = new RegisterUserDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void createUserAccount_usedEmail_badRequest() throws Exception {

        RegisterUserDto validUserRegistration = createValidRegisterUser();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
                        .content(objectMapper.writeValueAsString(validUserRegistration))
                        .contentType("application/json"))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
                        .content(objectMapper.writeValueAsString(validUserRegistration))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }


    @Test
    @Transactional
    void login_validUserCredential_successful() throws Exception {

        RegisterUserDto registerUser = createValidRegisterUser();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType("application/json"))
                .andExpect(status().isCreated());

        LoginUserDto loginUserDto = loginUserDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/login")
                        .content(objectMapper.writeValueAsString(loginUserDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    void login_invalidUserCredential_badRequest() throws Exception {

        RegisterUserDto registerUser = createValidRegisterUser();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType("application/json"))
                .andExpect(status().isCreated());

        LoginUserDto loginUserDto = new LoginUserDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/login")
                        .content(objectMapper.writeValueAsString(loginUserDto))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    void login_wrongCredentials_badRequest() throws Exception {

        RegisterUserDto registerUser = createValidRegisterUser();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType("application/json"))
                .andExpect(status().isCreated());

        LoginUserDto loginUserDto = new LoginUserDto()
                .setPassword("testttt")
                .setEmail("test@abv.bg");

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/login")
                        .content(objectMapper.writeValueAsString(loginUserDto))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DirtiesContext
    @WithMockUserPrincipal(username = "valid")
    void getUserDetails_withAuthorizeUser_successful() throws Exception {

        userServiceImp.register(createValidRegisterUser());

        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/user/details"))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    void getUserDetails_withNotAuthorizeUser_Forbidden() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/nutritionApi/user/details"))
                .andExpect(status().isForbidden());

    }

    @Test
    @Transactional
    void editUserProfile_withNotAuthorizeUser_Forbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/details/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUserPrincipal
    @DirtiesContext
    void editUserProfile_withAuthorizeUser_successful() throws Exception {
        userServiceImp.register(createValidRegisterUser());

        EditUserDto editUserDto = new EditUserDto()
                .setAge(56)
                .setGender(Gender.MALE)
                .setHeight(new BigDecimal(198))
                .setKilograms(new BigDecimal(86))
                .setWorkoutState(WorkoutState.MODERATELY_ACTIVE);

        mockMvc.perform(MockMvcRequestBuilders.patch("/nutritionApi/user/details/1")
                        .content(objectMapper.writeValueAsString(editUserDto))
                        .contentType("application/json"))
                .andExpect(status().isAccepted());
    }



    private RegisterUserDto createValidRegisterUser() {
        return new RegisterUserDto()
                .setEmail("valid@abv.bg")
                .setAge(23)
                .setPassword("valid")
                .setUsername("valid");
    }

    private LoginUserDto loginUserDto() {
        return new LoginUserDto()
                .setEmail("valid@abv.bg")
                .setPassword("valid");
    }

    private UserPrincipal getPrincipal(){
        UserEntity user = new UserEntity()
                .setEmail("valid@abv.bg")
                .setAge(23)
                .setPassword("valid")
                .setUsername("valid");
        user.setId(1L);

        return new UserPrincipal(user);
    }
}