package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.record.dto.NutrientChangeDto;
import com.example.nutrition_api.domain.record.dto.NutritionIntakeView;
import com.example.nutrition_api.domain.record.dto.RecordView;
import com.example.nutrition_api.domain.record.service.RecordServiceImp;
import com.example.nutrition_api.domain.users.entity.UserEntity;
import com.example.nutrition_api.domain.users.service.UserServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.IncorrectNutrientChangeException;
import com.example.nutrition_api.infrastructure.exceptions.RecordNotFoundException;
import com.example.nutrition_api.infrastructure.open_ai.RecordNutritionControllerDocs;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordNutritionController implements RecordNutritionControllerDocs {

    private final RecordServiceImp recordService;
    private final UserServiceImp userServiceImp;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COMPLETED')")
    public List<RecordView> getAll(Principal principal) {
        UserEntity user = userServiceImp.findByEmail(principal.getName());
        return recordService.getAllViewsByUserId(user.getId());
    }

    @GetMapping("/{day}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COMPLETED')")
    public RecordView getById(@PathVariable Long day) throws RecordNotFoundException {
        return recordService.getViewByRecordId(day);
    }

    @PatchMapping("/edit/{day}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('COMPLETED')")
    public NutritionIntakeView edit(
        @Valid @RequestBody NutrientChangeDto dto,
        BindingResult result,
        @PathVariable Long day,
        Principal principal
    ) throws IncorrectNutrientChangeException, RecordNotFoundException {
        if (result.hasErrors()) {
            throw new IncorrectNutrientChangeException(result.getFieldErrors());
        }

        String mail = principal.getName();
        UserEntity user = userServiceImp.findByEmail(mail);

        return recordService.updateRecordById(day, dto, user);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('COMPLETED')")
    public RecordView create(Principal principal) {
        String email = principal.getName();
        UserEntity user = userServiceImp.findByEmail(email);

        return recordService.addNewRecordByUserId(user.getId());
    }


    @DeleteMapping("/{day}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('COMPLETED')")
    public void delete(@PathVariable Long day, Principal principal) throws RecordNotFoundException {
        String email = principal.getName();
        UserEntity user = userServiceImp.findByEmail(email);
        recordService.deleteById(day, user);
    }

}
