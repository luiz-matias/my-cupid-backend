package com.luizmatias.findadev.api.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.luizmatias.findadev.api.dtos.mappers.UserTypeMapper.USER_TYPE_CLIENT;
import static com.luizmatias.findadev.api.dtos.mappers.UserTypeMapper.USER_TYPE_DEVELOPER;

public class UserTypeValidator implements ConstraintValidator<UserType, String> {

    @Override
    public void initialize(UserType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userTypeField, ConstraintValidatorContext cxt) {
        return userTypeField.equalsIgnoreCase(USER_TYPE_CLIENT) || userTypeField.equalsIgnoreCase(USER_TYPE_DEVELOPER);
    }

}