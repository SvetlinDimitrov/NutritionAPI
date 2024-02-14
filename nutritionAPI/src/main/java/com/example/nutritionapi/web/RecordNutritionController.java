package com.example.nutritionapi.web;

import com.example.nutritionapi.domain.dtos.NutrientChangeDto;
import com.example.nutritionapi.domain.dtos.viewDtos.NutritionIntakeView;
import com.example.nutritionapi.domain.dtos.viewDtos.RecordView;
import com.example.nutritionapi.domain.entity.UserEntity;
import com.example.nutritionapi.exceptions.IncorrectNutrientChangeException;
import com.example.nutritionapi.exceptions.RecordNotFoundException;
import com.example.nutritionapi.service.RecordServiceImp;
import com.example.nutritionapi.service.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
