package com.example.nutritionapi.web;

import com.example.nutritionapi.config.security.JwtAuthorizationFilter;
import com.example.nutritionapi.config.security.JwtUtil;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.repos.UserRepository;
import com.example.nutritionapi.service.UserServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtAuthorizationFilter filter;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void createUserAccount_validUserCreation_successful() throws Exception {
        RegisterUserDto registerUser = createValidRegisterUser();

        mockMvc.perform(MockMvcRequestBuilders.post("/nutritionApi/user/register")
                        .content(objectMapper.writeValueAsString(registerUser))
                        .contentType("application/json"))
                .andExpect(status().isCreated());


    }

    @Test
    void login() {
    }

    @Test
    void getUserDetails() {
    }

    @Test
    void editUserProfile() {
    }

    private RegisterUserDto createValidRegisterUser() {
        return new RegisterUserDto()
                .setEmail("test@abv.bg")
                .setAge(23)
                .setPassword("testtest")
                .setUsername("test");
    }
}