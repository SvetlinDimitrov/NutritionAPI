//package com.example.nutritionapi.service;
//
//import com.example.nutritionapi.feature.users.enums.Gender;
//import com.example.nutritionapi.feature.users.enums.WorkoutState;
//import com.example.nutritionapi.feature.record.dto.NutrientChangeDto;
//import com.example.nutritionapi.feature.record.dto.RecordView;
//import com.example.nutritionapi.feature.nutrition_intake.entity.NutritionIntakeEntity;
//import com.example.nutritionapi.feature.record.entity.RecordEntity;
//import com.example.nutritionapi.feature.users.entity.UserEntity;
//import com.example.nutritionapi.infrastructure.exceptions.IncorrectNutrientChangeException;
//import com.example.nutritionapi.infrastructure.exceptions.RecordNotFoundException;
//import com.example.nutritionapi.feature.record.repository.RecordRepository;
//import com.example.nutritionapi.feature.users.repository.UserRepository;
//import jakarta.validation.Valid;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class RecordServiceImpTest {
//
//    @Mock
//    private RecordRepository recordRepository;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private NutrientIntakeService nutrientIntakeService;
//    @InjectMocks
//    private RecordServiceImp recordServiceImp;
//
//    @Captor
//    private ArgumentCaptor<RecordEntity> recordCaptor;
//    @Captor
//    private ArgumentCaptor<UserEntity> userCaptor;
//
//    private UserEntity user;
//    private final List<RecordEntity> recordEntityList = new ArrayList<>();
//    private final List<RecordView> recordViews = new ArrayList<>();
//    private  Long VALID_ID = 1L;
//    private  Long INVALID_ID = 100L;
//
//
//    @BeforeEach
//    void setUp() {
//
//        user = new UserEntity()
//                .setGender(Gender.MALE)
//                .setAge(24)
//                .setHeight(new BigDecimal("189"))
//                .setWorkoutState(WorkoutState.SEDENTARY)
//                .setKilograms(new BigDecimal("84.1"));
//
//        RecordEntity record = createRecord(user);
//
//        record.setDailyIntakeViews(
//                List.of(new NutritionIntakeEntity()
//                        .setNutrientName("Carbohydrates")
//                        .setNutrientType("Macronutrient")
//                        .setMeasurement("grams (g)")
//                        .setLowerBoundIntake(new BigDecimal("400"))
//                        .setUpperBoundIntake(new BigDecimal("450"))
//                        .setRecord(record)));
//
//        recordEntityList.add(record);
//
//        //TODO:FIX THAT
////        recordViews.add(new RecordView(recordEntityList.get(0)));
//        user.setRecords(recordEntityList);
//    }
//
//    @Test
//    @Transactional
//    void getAllViewsByUserId_withValidUser_successful() {
//        when(userRepository.findById(VALID_ID)).thenReturn(Optional.of(user));
//
//        List<RecordView> result = recordServiceImp.getAllViewsByUserId(VALID_ID);
//
//        assertEquals(recordViews, result);
//    }
//
//    @Test
//    @Transactional
//    void getAllViewsByUserId_withInvalidUser_throwsUsernameNotFoundException() {
//        when(userRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
//
//        assertThrows(UsernameNotFoundException.class, () -> recordServiceImp.getAllViewsByUserId(INVALID_ID));
//    }
//
//    @Test
//    @Transactional
//    void getViewByRecordId_withValidId_successful() throws RecordNotFoundException {
//        when(recordRepository.findById(VALID_ID)).thenReturn(Optional.ofNullable(recordEntityList.get(0)));
//
//        RecordView result = recordServiceImp.getViewByRecordId(VALID_ID);
//        RecordView excepted = recordViews.get(0);
//
//        assertEquals(excepted, result);
//    }
//
//    @Test
//    @Transactional
//    void getViewByRecordId_withInvalidId_throwsRecordNotFoundException() {
//        when(recordRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
//
//        assertThrows(RecordNotFoundException.class, () -> recordServiceImp.getViewByRecordId(INVALID_ID));
//    }
//
//    @Test
//    @Transactional
//    void updateRecordById_invalidRecordId_throwsRecordNotFoundException() {
//
//        NutrientChangeDto changeDto = new NutrientChangeDto("invalid" , new BigDecimal("100"));
//        assertThrows(RecordNotFoundException.class, () -> recordServiceImp.updateRecordById(INVALID_ID, changeDto , user));
//    }
//
//    @Test
//    @Transactional
//    void updateRecordById_invalidNutritionName_throwsIncorrectNutrientChangeException() {
//
//        NutrientChangeDto changeDto = new NutrientChangeDto("invalid" ,new BigDecimal("100"));
//        VALID_ID = user.getRecords().get(0).getId();
//
//        assertThrows(IncorrectNutrientChangeException.class, () -> recordServiceImp.updateRecordById(VALID_ID, changeDto, user));
//    }
//
//    @Test
//    @Transactional
//    void updateRecordById_validParameters_successful() throws RecordNotFoundException, IncorrectNutrientChangeException {
//        NutrientChangeDto changeDto = new NutrientChangeDto("Carbohydrates" , new BigDecimal("100"));
//
//        VALID_ID = user.getRecords().get(0).getId();
//
//        RecordEntity expected = recordEntityList.get(0);
//        expected.getDailyIntakeViews().get(0).setDailyConsumed(new BigDecimal("100"));
//
//        recordServiceImp.updateRecordById(VALID_ID, changeDto, user);
//
//        verify(recordRepository , times(1)).save(recordCaptor.capture());
//
//        RecordEntity result = recordCaptor.getValue();
//
//        assertEquals(expected , result);
//    }
//
//    @Test
//    @Transactional
//    void addNewRecordByUserId_validUserId_successful() {
//        when(userRepository.findById(VALID_ID)).thenReturn(Optional.ofNullable(user));
//
//        recordServiceImp.addNewRecordByUserId(VALID_ID);
//
//        verify(userRepository , times(1)).save(userCaptor.capture());
//
//        UserEntity result = userCaptor.getValue();
//
//        assertEquals(user , result);
//    }
//
//    @Test
//    @Transactional
//    void addNewRecordByUserId_invalidUserId_throwsUsernameNotFoundException() {
//
//        when(userRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
//
//        assertThrows(UsernameNotFoundException.class ,
//                () -> recordServiceImp.addNewRecordByUserId(INVALID_ID));
//    }
//
//    @Test
//    @Transactional
//    void deleteById_validRecordId_successful() throws RecordNotFoundException {
//        VALID_ID = user.getRecords().get(0).getId();
//
//        recordServiceImp.deleteById(VALID_ID, user);
//
//        verify(recordRepository , times(1)).deleteById(VALID_ID);
//    }
//
//    @Test
//    @Transactional
//    void deleteById_invalidRecordId_throwsRecordNotFoundException() {
//        INVALID_ID = user.getRecords().size() + 1L;
//
//        assertThrows(RecordNotFoundException.class , () ->recordServiceImp.deleteById(INVALID_ID, user));
//    }
//
//    private RecordEntity createRecord(UserEntity user) {
//        RecordEntity record = new RecordEntity();
//        record.setId(1L);
//        record.setUser(user);
//        BigDecimal BMR;
//
//        if (user.getGender().equals(Gender.MALE)) {
//            BMR = new BigDecimal("88.362")
//                    .add(new BigDecimal("13.397").multiply(user.getKilograms()))
//                    .add(new BigDecimal("4.799").multiply(user.getHeight()))
//                    .subtract(new BigDecimal("5.677").add(new BigDecimal(user.getAge())));
//        } else {
//            BMR = new BigDecimal("447.593 ")
//                    .add(new BigDecimal("9.247").multiply(user.getKilograms()))
//                    .add(new BigDecimal("3.098").multiply(user.getHeight()))
//                    .subtract(new BigDecimal("4.330").add(new BigDecimal(user.getAge())));
//        }
//
//        BigDecimal caloriesPerDay = switch (user.getWorkoutState()) {
//            case SEDENTARY -> BMR.multiply(new BigDecimal("1.2"));
//            case LIGHTLY_ACTIVE -> BMR.multiply(new BigDecimal("1.375"));
//            case MODERATELY_ACTIVE -> BMR.multiply(new BigDecimal("1.55"));
//            case VERY_ACTIVE -> BMR.multiply(new BigDecimal("1.725"));
//            case SUPER_ACTIVE -> BMR.multiply(new BigDecimal("1.9"));
//        };
//
//        record.setDailyCalories(caloriesPerDay);
//        record.setDailyIntakeViews(nutrientIntakeService
//                .create(user.getGender(), caloriesPerDay, user.getWorkoutState(), record));
//        return record;
//    }
//}