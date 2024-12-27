package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.record.dto.NutrientChangeDto;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.service.RecordServiceImp;
import com.example.nutrition_api.domain.users.entity.UserEntity;
import com.example.nutrition_api.domain.users.service.UserServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.IncorrectNutrientChangeException;
import com.example.nutrition_api.infrastructure.exceptions.RecordNotFoundException;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutritionApi/records")
public class RecordNutritionController {

    private final RecordServiceImp recordService;
    private final UserServiceImp userServiceImp;

    public RecordNutritionController(RecordServiceImp recordService, UserServiceImp userServiceImp) {
        this.recordService = recordService;
        this.userServiceImp = userServiceImp;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecordView> getAllRecords(Principal principal) {
        UserEntity user = userServiceImp.findByEmail(principal.getName());
        return recordService.getAllViewsByUserId(user.getId());
    }

    @GetMapping("/{day}")
    @ResponseStatus(HttpStatus.OK)
    public RecordView getById(@PathVariable Long day) throws RecordNotFoundException {
        return recordService.getViewByRecordId(day);
    }

    @PatchMapping("/edit/{day}")
    @ResponseStatus(HttpStatus.CREATED)
    public NutritionIntakeView changeNutrientByRecordDay(@Valid @RequestBody NutrientChangeDto dto,
                                                         BindingResult result,
                                                         @PathVariable Long day,
                                                         Principal principal) throws IncorrectNutrientChangeException, RecordNotFoundException {
        if (result.hasErrors()) {
            throw new IncorrectNutrientChangeException(result.getFieldErrors());
        }

        String mail = principal.getName();
        UserEntity user = userServiceImp.findByEmail(mail);

        return recordService.updateRecordById(day, dto, user);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecordView createNewRecord(Principal principal) {
        String email = principal.getName();
        UserEntity user = userServiceImp.findByEmail(email);

        return recordService.addNewRecordByUserId(user.getId());
    }


    @DeleteMapping("/{day}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecord(@PathVariable Long day, Principal principal) throws RecordNotFoundException {
        String email = principal.getName();
        UserEntity user = userServiceImp.findByEmail(email);
        recordService.deleteById(day, user);
    }

}
