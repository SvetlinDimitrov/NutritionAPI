package com.example.nutrition_api.infrastructure.mappers;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.domain.macros.entity.Macronutrient;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.entity.NutritionIntakeEntity;
import com.example.nutrition_api.domain.record.entity.RecordEntity;
import com.example.nutrition_api.domain.shared.dto.PairView;
import com.example.nutrition_api.domain.users.dto.UserView;
import com.example.nutrition_api.domain.users.entity.UserEntity;
import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import com.example.nutrition_api.domain.vitamin.entity.Vitamin;
import org.springframework.stereotype.Component;

@Component
public class ViewConverter {

    public ElectrolyteView toView(Electrolyte entity) {
        return new ElectrolyteView(
                entity.getName(),
                entity.getDescription(),
                entity.getFunctions().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
                entity.getSources().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
                entity.getHealthConsiderations().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
                entity.getMaleLowerBoundIntake(),
                entity.getMaleHigherBoundIntake(),
                entity.getFemaleLowerBoundIntake(),
                entity.getFemaleHigherBoundIntake(),
                entity.getMeasure());
    }

    public MacronutrientView toView(Macronutrient entity) {
        return new MacronutrientView(
                entity.getName(),
                entity.getDescription(),
                entity.getActiveState(),
                entity.getInactiveState(),
                entity.getFunctions().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
                entity.getSources().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
                entity.getTypes().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
                entity.getDietaryConsiderations().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList()
        );
    }

    public NutritionIntakeView toView(NutritionIntakeEntity entity) {
        return new NutritionIntakeView(
                entity.getId(),
                entity.getNutrientName(),
                entity.getNutrientType(),
                entity.getLowerBoundIntake(),
                entity.getUpperBoundIntake(),
                entity.getDailyConsumed(),
                entity.getMeasurement(),
                entity.getRecord().getId()
        );
    }

    public RecordView toView(RecordEntity entity) {
        return new RecordView(
                entity.getId(),
                entity.getDailyIntakeViews().stream().map(this::toView).toList(),
                entity.getDailyCalories(),
                entity.getUser().getId(),
                entity.getUser().getUsername()
        );
    }

    public UserView toView(UserEntity entity) {
        return new UserView(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getKilograms(),
                entity.getHeight(),
                entity.getWorkoutState(),
                entity.getGender(),
                entity.getUserDetails(),
                entity.getAge()
        );
    }

    public VitaminView toView(Vitamin entity) {
        return new VitaminView(
                entity.getName(),
                entity.getDescription(),
                entity.getFunctions().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
                entity.getSources().stream().map(p -> new PairView(p.getKey(), p.getValue())).toList(),
                entity.getMaleLowerBoundIntake(),
                entity.getMaleHigherBoundIntake(),
                entity.getFemaleLowerBoundIntake(),
                entity.getFemaleHigherBoundIntake(),
                entity.getMeasure()
        );
    }
}
