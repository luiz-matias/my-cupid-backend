package com.luizmatias.mycupid.api.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserTypeValidator implements ConstraintValidator<UserType, String> {

    @Override
    public void initialize(UserType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userTypeField, ConstraintValidatorContext cxt) {
        return userTypeField != null && (
                userTypeField.equalsIgnoreCase(com.luizmatias.mycupid.domain.entities.UserType.CLIENT.getType()) ||
                        userTypeField.equalsIgnoreCase(com.luizmatias.mycupid.domain.entities.UserType.DEVELOPER.getType())
        );
    }

}