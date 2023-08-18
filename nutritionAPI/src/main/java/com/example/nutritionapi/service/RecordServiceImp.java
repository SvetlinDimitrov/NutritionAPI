package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.AllNutrientsNames;
import com.example.nutritionapi.domain.dtos.NutrientChangeDto;
import com.example.nutritionapi.domain.dtos.viewDtos.NutritionIntakeView;
import com.example.nutritionapi.domain.dtos.viewDtos.RecordView;
import com.example.nutritionapi.domain.entity.NutritionIntake;
import com.example.nutritionapi.domain.entity.RecordEntity;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.exceptions.IncorrectNutrientChange;
import com.example.nutritionapi.exceptions.RecordNotFound;
import com.example.nutritionapi.repos.RecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecordServiceImp {
    private final RecordRepository recordRepository;
    private final UserServiceImp userServiceImp;

    public RecordServiceImp(RecordRepository recordRepository, UserServiceImp userServiceImp) {
        this.recordRepository = recordRepository;
        this.userServiceImp = userServiceImp;
    }

    public List<RecordView> getAllViewsByUserId(Long userId){
        UserEntity user = userServiceImp.findById(userId);
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

        intake.setDailyConsumed(dto.getMeasure());
        recordRepository.save(record);

        return new NutritionIntakeView(intake);
    }
}
