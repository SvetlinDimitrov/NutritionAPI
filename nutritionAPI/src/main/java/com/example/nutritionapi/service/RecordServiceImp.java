package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.enums.Gender;
import com.example.nutritionapi.domain.dtos.NutrientChangeDto;
import com.example.nutritionapi.domain.dtos.viewDtos.NutritionIntakeView;
import com.example.nutritionapi.domain.dtos.viewDtos.RecordView;
import com.example.nutritionapi.domain.entity.NutritionIntakeEntity;
import com.example.nutritionapi.domain.entity.RecordEntity;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.exceptions.IncorrectNutrientChangeException;
import com.example.nutritionapi.exceptions.RecordNotFoundException;
import com.example.nutritionapi.repos.RecordRepository;
import com.example.nutritionapi.repos.UserRepository;
import com.example.nutritionapi.utils.ViewConverter;
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
    private final ViewConverter converter;

    public RecordServiceImp(RecordRepository recordRepository, UserRepository userRepository, NutrientIntakeService nutrientIntakeService, ViewConverter converter) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.nutrientIntakeService = nutrientIntakeService;
        this.converter = converter;
    }

    public List<RecordView> getAllViewsByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId.toString()));
        return user.getRecords().stream()
                .map(converter::toView)
                .toList();
    }

    public RecordView getViewByRecordId(Long day) throws RecordNotFoundException {
        return recordRepository.findById(day)
                .map(converter::toView)
                .orElseThrow(() -> new RecordNotFoundException(day.toString()));
    }

    @Transactional
    public NutritionIntakeView updateRecordById(Long day, NutrientChangeDto dto, UserEntity user) throws RecordNotFoundException, IncorrectNutrientChangeException {

        RecordEntity record = user.getRecords()
                .stream()
                .filter(r -> r.getId().equals(day))
                .findAny()
                .orElseThrow(() -> new RecordNotFoundException(day.toString()));

        NutritionIntakeEntity intake = record.getDailyIntakeViews()
                .stream()
                .filter(nutrient -> nutrient.getNutrientName().equals(dto.name()))
                .findFirst()
                .orElseThrow(() -> new IncorrectNutrientChangeException("catch me"));

        intake.setDailyConsumed(intake.getDailyConsumed().add(dto.measure()));
        recordRepository.save(record);

        return converter.toView(intake);
    }

    public RecordView addNewRecordByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId.toString()));

        RecordEntity record = createRecord(user);

        user.getRecords().add(record);
        userRepository.save(user);

        return converter.toView(user.getRecords().get(user.getRecords().size() - 1));
    }

    public RecordEntity createRecord(UserEntity user) {
        RecordEntity record = new RecordEntity();
        record.setUser(user);
        BigDecimal BMR = getBmr(user);

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

    private BigDecimal getBmr(UserEntity user) {
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
        return BMR;
    }

    @Transactional
    public void deleteById(Long day, UserEntity user) throws RecordNotFoundException {

        user.getRecords()
                .stream()
                .filter(r -> r.getId().equals(day))
                .findAny()
                .orElseThrow(() -> new RecordNotFoundException(day.toString()));

        recordRepository.deleteById(day);
    }
}
