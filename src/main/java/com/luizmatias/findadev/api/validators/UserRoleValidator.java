package com.luizmatias.findadev.api.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserRoleValidator implements ConstraintValidator<UserRole, String> {

    @Override
    public void initialize(UserRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userRoleField, ConstraintValidatorContext cxt) {
        return userRoleField != null && (
                userRoleField.equalsIgnoreCase(com.luizmatias.findadev.domain.entities.UserRole.USER.getRole()) ||
                        userRoleField.equalsIgnoreCase(com.luizmatias.findadev.domain.entities.UserRole.ADMIN.getRole())
        );
    }

}