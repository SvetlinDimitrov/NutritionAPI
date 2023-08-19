package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.AllNutrientsNames;
import com.example.nutritionapi.domain.constants.Gender;
import com.example.nutritionapi.domain.dtos.NutrientChangeDto;
import com.example.nutritionapi.domain.dtos.viewDtos.NutritionIntakeView;
import com.example.nutritionapi.domain.dtos.viewDtos.RecordView;
import com.example.nutritionapi.domain.entity.NutritionIntake;
import com.example.nutritionapi.domain.entity.RecordEntity;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.exceptions.IncorrectNutrientChange;
import com.example.nutritionapi.exceptions.RecordNotFound;
import com.example.nutritionapi.repos.RecordRepository;
import com.example.nutritionapi.repos.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RecordServiceImp {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final NutrientIntakeService nutrientIntakeService;

    public RecordServiceImp(RecordRepository recordRepository, UserRepository userRepository, NutrientIntakeService nutrientIntakeService) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.nutrientIntakeService = nutrientIntakeService;
    }

    public List<RecordView> getAllViewsByUserId(Long userId){
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId.toString()));
        return user.getRecords().stream()
                .map(RecordView::new)
                .toList();
    }

    public RecordView getViewByRecordId(Long day) throws RecordNotFound {
        return recordRepository.findById(day)
                .map(RecordView::new)
                .orElseThrow(() -> new RecordNotFound(day.toString()));
    }

    @Transactional
    public NutritionIntakeView updateRecordById(Long day, NutrientChangeDto dto) throws RecordNotFound, IncorrectNutrientChange {

        RecordEntity record = recordRepository.findById(day).orElseThrow(() -> new RecordNotFound(day.toString()));

        NutritionIntake intake = record.getDailyIntakeViews()
                .stream()
                .filter(nutrient -> nutrient.getNutrientName().equals(dto.getName()))
                .findFirst()
                .orElseThrow(() -> new IncorrectNutrientChange("This does not match any nutrient in the dalyRecord/dataBase \n"+ AllNutrientsNames.getAllNutritionNames()));

        intake.setDailyConsumed(intake.getDailyConsumed().add(dto.getMeasure()));
        recordRepository.save(record);

        return new NutritionIntakeView(intake);
    }

    public RecordView addNewRecordByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId.toString()));

        RecordEntity record = createRecord(user);

        user.getRecords().add(record);
        userRepository.save(user);

        return new RecordView(user.getRecords().get(user.getRecords().size()-1));
    }
    public RecordEntity createRecord(UserEntity user) {
        RecordEntity record = new RecordEntity();
        record.setUser(user);
        BigDecimal BMR;

        if (user.getGender().equals(Gender.MALE)) {
            BMR = new BigDecimal("88.362")
                    .add(new BigDecimal("13.397").multiply(user.getKilograms()))
                    .add(new BigDecimal("4.799").multiply(user.getHeight()))
                    .subtract(new BigDecimal("5.677").add(new BigDecimal(user.getAge())));
        } else {
            BMR = new BigDecimal("447.593 ")
                    .add(new BigDecimal("9.247").multiply(user.getKilograms()))
                    .add(new BigDecimal("3.098").multiply(user.getHeight()))
                    .subtract(new BigDecimal("4.330").add(new BigDecimal(user.getAge())));
        }

        BigDecimal caloriesPerDay = switch (user.getWorkoutState()) {
            case SEDENTARY -> BMR.multiply(new BigDecimal("1.2"));
            case LIGHTLY_ACTIVE -> BMR.multiply(new BigDecimal("1.375"));
            case MODERATELY_ACTIVE -> BMR.multiply(new BigDecimal("1.55"));
            case VERY_ACTIVE -> BMR.multiply(new BigDecimal("1.725"));
            case SUPER_ACTIVE -> BMR.multiply(new BigDecimal("1.9"));
        };

        record.setDailyCalories(caloriesPerDay);
        record.setDailyIntakeViews(nutrientIntakeService
                .create(user.getGender(), caloriesPerDay, user.getWorkoutState(), record));
        return record;
    }

    @Transactional
    public void deleteById(Long day) throws RecordNotFound {

        RecordEntity record = recordRepository.findById(day).orElseThrow(() -> new RecordNotFound(day.toString()));

        recordRepository.deleteById(day);
    }
}
