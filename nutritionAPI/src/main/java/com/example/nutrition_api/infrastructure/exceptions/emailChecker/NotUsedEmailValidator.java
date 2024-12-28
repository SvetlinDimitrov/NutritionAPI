package com.example.nutrition_api.infrastructure.exceptions.emailChecker;

import com.example.nutrition_api.domain.users.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NotUsedEmailValidator implements ConstraintValidator<NotUsedEmailConstraint, String> {

    private final UserService service;

    public NotUsedEmailValidator(UserService service) {
        this.service = service;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return service.notUsedEmail(value);
    }
}
