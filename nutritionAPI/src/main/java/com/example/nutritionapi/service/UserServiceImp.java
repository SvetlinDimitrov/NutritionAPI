package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.Gender;
import com.example.nutritionapi.domain.constants.UserDetails;
import com.example.nutritionapi.domain.dtos.user.EditUserDto;
import com.example.nutritionapi.domain.dtos.user.LoginUserDto;
import com.example.nutritionapi.domain.dtos.user.RegisterUserDto;
import com.example.nutritionapi.domain.dtos.viewDtos.UserView;
import com.example.nutritionapi.domain.entity.RecordEntity;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.repos.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImp {
    private final UserRepository userRepository;
    private final NutrientIntakeService nutrientIntakeService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImp(UserRepository userRepository, NutrientIntakeService nutrientIntakeService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.nutrientIntakeService = nutrientIntakeService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean notUsedEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public void register(RegisterUserDto userDto) {
        UserEntity entity = toUserEntity(userDto);

        fillUserWithCompleteDetails(entity);
    }

    private UserEntity toUserEntity(RegisterUserDto userDto) {
        return new UserEntity()
                .setEmail(userDto.getEmail())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setUsername(userDto.getUsername())
                .setWorkoutState(userDto.getWorkoutState())
                .setKilograms(userDto.getKg())
                .setGender(userDto.getGender())
                .setHeight(userDto.getHeight())
                .setAge(userDto.getAge());
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("no user found with the given email"));
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("no user found with the given id"));
    }

    public boolean login(LoginUserDto userDto) {
        Optional<UserEntity> entity = userRepository.findByEmail(userDto.getEmail());
        return entity.isPresent() && passwordEncoder.matches(userDto.getPassword(), entity.get().getPassword());
    }

    public UserView getUserViewById(Long userId) {
        return new UserView(findById(userId));
    }

    public void editUserEntity(EditUserDto userDto, Long userId) {
        UserEntity user = findById(userId);

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getKilograms() != null) {
            user.setKilograms(userDto.getKilograms());
        }
        if (userDto.getWorkoutState() != null) {
            user.setWorkoutState(userDto.getWorkoutState());
        }
        if (userDto.getHeight() != null) {
            user.setHeight(userDto.getHeight());
        }
        if (userDto.getGender() != null) {
            user.setGender(userDto.getGender());
        }
        if (userDto.getAge() != null) {
            user.setAge(userDto.getAge());
        }


        fillUserWithCompleteDetails(user);
    }

    private RecordEntity createRecord(UserEntity user) {
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

    private void fillUserWithCompleteDetails(UserEntity entity) {
        if (entity.getKilograms() != null &&
                entity.getWorkoutState() != null &&
                entity.getGender() != null &&
                entity.getHeight() != null &&
                entity.getAge() != null &&
                (entity.getRecords() == null || entity.getRecords().isEmpty())) {
            entity.setUserDetails(UserDetails.COMPLETED);
            entity.getRecords().add(createRecord(entity));
        }

        userRepository.save(entity);
    }

}
