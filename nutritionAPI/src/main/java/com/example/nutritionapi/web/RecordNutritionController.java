package com.example.nutritionapi.web;

import com.example.nutritionapi.config.security.UserPrincipal;
import com.example.nutritionapi.domain.dtos.NutrientChangeDto;
import com.example.nutritionapi.domain.dtos.viewDtos.NutritionIntakeView;
import com.example.nutritionapi.domain.dtos.viewDtos.RecordView;
import com.example.nutritionapi.exceptions.IncorrectNutrientChange;
import com.example.nutritionapi.exceptions.RecordNotFound;
import com.example.nutritionapi.service.RecordServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutritionApi/records")
public class RecordNutritionController {

    private final RecordServiceImp recordService;

    public RecordNutritionController(RecordServiceImp recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public ResponseEntity<List<RecordView>> getAllRecords(@AuthenticationPrincipal UserPrincipal userPrincipal){
        List<RecordView> records = recordService.getAllViewsByUserId(userPrincipal.getId());
        return new ResponseEntity<>(records , HttpStatus.FOUND);
    }

    @GetMapping("/{day}")
    public ResponseEntity<RecordView> getById(@PathVariable Long day) throws RecordNotFound {
        RecordView record = recordService.getViewByRecordId(day);
        return new ResponseEntity<>(record , HttpStatus.FOUND);
    }

    @PatchMapping("/edit/{day}")
    public ResponseEntity<NutritionIntakeView> changeNutrientByRecordDay(@Valid @RequestBody NutrientChangeDto dto,
                                                                         BindingResult result,
                                                                         @PathVariable Long day) throws IncorrectNutrientChange, RecordNotFound {
        if(result.hasErrors()){
            throw new IncorrectNutrientChange(result.getFieldErrors());
        }

        NutritionIntakeView changedNutrient = recordService.updateRecordById(day , dto);
        return new ResponseEntity<>(changedNutrient , HttpStatus.OK);
    }


    @PostMapping("/endDay")
    public ResponseEntity<RecordView> createNewRecord(@AuthenticationPrincipal UserPrincipal principal){
        RecordView view = recordService.addNewRecordByUserId(principal.getId());
        return new ResponseEntity<>(view , HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{day}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long day) throws RecordNotFound {
        recordService.deleteById(day);
        return ResponseEntity.ok().body("Successfully deleted");
    }

}
