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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<RecordView>> getAllRecords(Principal principal){
        UserEntity user = userServiceImp.findByEmail(principal.getName());
        List<RecordView> records = recordService.getAllViewsByUserId(user.getId());
        return new ResponseEntity<>(records , HttpStatus.FOUND);
    }

    @GetMapping("/{day}")
    public ResponseEntity<RecordView> getById(@PathVariable Long day) throws RecordNotFoundException {
        RecordView record = recordService.getViewByRecordId(day);
        return new ResponseEntity<>(record , HttpStatus.FOUND);
    }

    @PatchMapping("/edit/{day}")
    public ResponseEntity<NutritionIntakeView> changeNutrientByRecordDay(@Valid @RequestBody NutrientChangeDto dto,
                                                                         BindingResult result,
                                                                         @PathVariable Long day) throws IncorrectNutrientChangeException, RecordNotFoundException {
        if(result.hasErrors()){
            throw new IncorrectNutrientChangeException(result.getFieldErrors());
        }

        NutritionIntakeView changedNutrient = recordService.updateRecordById(day , dto);
        return new ResponseEntity<>(changedNutrient , HttpStatus.OK);
    }


    @PostMapping("/endDay")
    public ResponseEntity<RecordView> createNewRecord(Principal principal){
        String email = principal.getName();
        UserEntity user = userServiceImp.findByEmail(email);

        RecordView view = recordService.addNewRecordByUserId(user.getId());
        return new ResponseEntity<>(view , HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{day}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long day) throws RecordNotFoundException {
        recordService.deleteById(day);
        return ResponseEntity.ok().body("Successfully deleted");
    }

}
