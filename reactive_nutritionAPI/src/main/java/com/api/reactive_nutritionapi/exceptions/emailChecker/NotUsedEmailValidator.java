//package com.api.reactive_nutritionapi.exceptions.emailChecker;
//
//import com.api.reactive_nutritionapi.service.UserServiceImp;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.springframework.stereotype.Component;
//
//@Component
//public class NotUsedEmailValidator implements ConstraintValidator<NotUsedEmailConstraint, String> {
//    private final UserServiceImp service;
//
//    public NotUsedEmailValidator(UserServiceImp service) {
//        this.service = service;
//    }
//
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return service.notUsedEmail(value);
//    }
//}
