package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.constants.enums.UserDetails;
import com.example.nutritionapi.domain.constants.enums.WorkoutState;
import com.example.nutritionapi.domain.dtos.user.EditUserDto;
import com.example.nutritionapi.domain.dtos.user.LoginUserDto;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.domain.dtos.viewDtos.UserView;
import com.example.nutritionapi.domain.entity.RecordEntity;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private  UserRepository userRepository;
    @Mock
    private  RecordServiceImp recordService;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImp userServiceImp;
    @Captor
    private ArgumentCaptor<UserEntity> argumentCaptor;

    private UserEntity user;
    private UserView userView;
    private final String VALID_EMAIL = "valid@abv.bg";
    private final String INVALID_EMAIL = "invalid";

    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = 100L;

    @BeforeEach
    void setUp() {
        user = new UserEntity()
                .setEmail("test@abv.bg")
                .setPassword("test")
                .setUsername("test")
                .setWorkoutState(WorkoutState.SEDENTARY)
                .setKilograms(new BigDecimal("90"))
                .setGender(Gender.MALE)
                .setHeight(new BigDecimal("191"))
                .setRecords(new ArrayList<>())
                .setAge(76);

        userView = new UserView(user);

    }

    @Test
    void notUsedEmail_withNotUsedEmail_true() {
        when(userRepository.findByEmail(VALID_EMAIL)).thenReturn(Optional.empty());

        assertTrue(userServiceImp.notUsedEmail(VALID_EMAIL));
    }

    @Test
    void notUsedEmail_withUsedEmail_false() {
        when(userRepository.findByEmail(INVALID_EMAIL)).thenReturn(Optional.ofNullable(user));

        assertFalse(userServiceImp.notUsedEmail(INVALID_EMAIL));
    }

    @Test
    void register_withFullyRegistration_userDetailsComplete() {
        RegisterUserDto registerUserDto = new RegisterUserDto()
                .setEmail("test@abv.bg")
                .setPassword("test")
                .setUsername("test")
                .setWorkoutState(WorkoutState.SEDENTARY)
                .setKg(new BigDecimal("90"))
                .setGender(Gender.MALE)
                .setHeight(new BigDecimal("191"))
                .setAge(76);

        when(passwordEncoder.encode(user.getPassword())).thenReturn("test");

        userServiceImp.register(registerUserDto);

        verify(userRepository , times(1)).save(argumentCaptor.capture());

        UserEntity result = argumentCaptor.getValue();

        assertEquals(UserDetails.COMPLETED , result.getUserDetails());
    }

    @Test
    void register_withNotFullyRegistration_userDetailsNot_Complete() {
        RegisterUserDto registerUserDto = new RegisterUserDto()
                .setEmail("test@abv.bg")
                .setPassword("test")
                .setUsername("test");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("test");

        userServiceImp.register(registerUserDto);

        verify(userRepository , times(1)).save(argumentCaptor.capture());

        UserEntity result = argumentCaptor.getValue();

        assertEquals(UserDetails.NOT_COMPLETED , result.getUserDetails());
    }

    @Test
    void findByEmail_validEmail_successful() {
        when(userRepository.findByEmail(VALID_EMAIL)).thenReturn(Optional.ofNullable(user));

        UserEntity result = userServiceImp.findByEmail(VALID_EMAIL);

        assertEquals(user, result);
    }

    @Test
    void findByEmail_invalidEmail_throwsUsernameNotFoundException() {
        when(userRepository.findByEmail(INVALID_EMAIL)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class , () ->userServiceImp.findByEmail(INVALID_EMAIL));
    }

    @Test
    void findById_validID_successful() {
        when(userRepository.findById(VALID_ID)).thenReturn(Optional.ofNullable(user));

        UserEntity result = userServiceImp.findById(VALID_ID);

        assertEquals(user , result);
    }

    @Test
    void findById_invalidID_throwsUsernameNotFoundException() {
        when(userRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class , () ->userServiceImp.findById(INVALID_ID));
    }

    @Test
    void login_validPasswordAndValidUserEmail_true() {
        LoginUserDto loginUserDto = new LoginUserDto()
                .setEmail("test@abv.bg")
                .setPassword("test");

        when(passwordEncoder.matches(loginUserDto.getPassword(),user.getPassword())).thenReturn(true);
        when(userRepository.findByEmail(loginUserDto.getEmail())).thenReturn(Optional.ofNullable(user));

        assertTrue(userServiceImp.login(loginUserDto));
    }

    @Test
    void login_invalidPasswordAndValidUserEmail_false() {
        LoginUserDto loginUserDto = new LoginUserDto()
                .setEmail("test@abv.bg")
                .setPassword("");

        when(passwordEncoder.matches(loginUserDto.getPassword(),user.getPassword())).thenReturn(false);
        when(userRepository.findByEmail(loginUserDto.getEmail())).thenReturn(Optional.ofNullable(user));

        assertFalse(userServiceImp.login(loginUserDto));
    }

    @Test
    void login_validPasswordAndInvalidUserEmail_false() {
        LoginUserDto loginUserDto = new LoginUserDto()
                .setEmail("")
                .setPassword("test");
        when(userRepository.findByEmail(loginUserDto.getEmail())).thenReturn(Optional.ofNullable(user));

        assertFalse(userServiceImp.login(loginUserDto));
    }

    @Test
    void getUserViewById_validId_successfulyConverted() {
        when(userRepository.findById(VALID_ID)).thenReturn(Optional.ofNullable(user));

        UserView result = userServiceImp.getUserViewById(VALID_ID);

        assertEquals(userView , result);
    }
    @Test
    void getUserViewById_invalidId_throwsUsernameNotFoundException() {
        when(userRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class , () ->userServiceImp.getUserViewById(INVALID_ID));

    }

    @Test
    void editUserEntity_invalidUserId_throwsUsernameNotFoundException() {
        when(userRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class , () ->userServiceImp.getUserViewById(INVALID_ID));
    }

    @Test
    void editUserEntity_withFillEditInformation_UserDetailsComplete() {
        UserEntity user2 = new UserEntity()
                .setEmail("test2@abv.bg")
                .setPassword("test2")
                .setUsername("test2");

        EditUserDto editUserDto = new EditUserDto()
                .setUsername("test2")
                .setWorkoutState(WorkoutState.SUPER_ACTIVE)
                .setKilograms(new BigDecimal("80"))
                .setGender(Gender.FEMALE)
                .setHeight(new BigDecimal("194"))
                .setAge(34);



        when(userRepository.findById(VALID_ID)).thenReturn(Optional.of(user2));

        userServiceImp.editUserEntity(editUserDto , VALID_ID);

        verify(userRepository , times(1)).save(argumentCaptor.capture());

        UserEntity result = argumentCaptor.getValue();

        assertEquals(UserDetails.COMPLETED , result.getUserDetails());
    }

    @Test
    void editUserEntity_withNotFillyEditInformation_UserDetailsNotComplete() {
        UserEntity user2 = new UserEntity()
                .setEmail("test2@abv.bg")
                .setPassword("test2")
                .setUsername("test2");

        EditUserDto editUserDto = new EditUserDto()
                .setUsername("test2")
                .setWorkoutState(WorkoutState.SUPER_ACTIVE)
                .setAge(34);



        when(userRepository.findById(VALID_ID)).thenReturn(Optional.of(user2));

        userServiceImp.editUserEntity(editUserDto , VALID_ID);

        verify(userRepository , times(1)).save(argumentCaptor.capture());

        UserEntity result = argumentCaptor.getValue();

        assertEquals(UserDetails.NOT_COMPLETED , result.getUserDetails());
    }

    @Test
    void editUserEntity_convertedCorrectly_UserDetailsComplete() {
        UserEntity excepted = new UserEntity()
                .setEmail("test2@abv.bg")
                .setPassword("test")
                .setUsername("test2")
                .setWorkoutState(WorkoutState.SUPER_ACTIVE)
                .setKilograms(new BigDecimal("80"))
                .setGender(Gender.FEMALE)
                .setRecords(new ArrayList<>())
                .setHeight(new BigDecimal("194"))
                .setUserDetails(UserDetails.COMPLETED)
                .setAge(34);
        excepted.getRecords().add(null);

        EditUserDto editUserDto = new EditUserDto()
                .setUsername("test2")
                .setWorkoutState(WorkoutState.SUPER_ACTIVE)
                .setKilograms(new BigDecimal("80"))
                .setGender(Gender.FEMALE)
                .setHeight(new BigDecimal("194"))
                .setAge(34);



        when(userRepository.findById(VALID_ID)).thenReturn(Optional.of(user));

        userServiceImp.editUserEntity(editUserDto , VALID_ID);

        verify(userRepository , times(1)).save(argumentCaptor.capture());

        UserEntity result = argumentCaptor.getValue();

        assertEquals(excepted , result);
    }
}