package com.luizmatias.findadev.api.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderTypeValidator implements ConstraintValidator<OrderType, String> {

    @Override
    public void initialize(OrderType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String orderTypeField, ConstraintValidatorContext cxt) {
        return orderTypeField != null && (
                orderTypeField.equalsIgnoreCase(com.luizmatias.findadev.domain.entities.pagination.OrderType.ASC.getOrder()) ||
                        orderTypeField.equalsIgnoreCase(com.luizmatias.findadev.domain.entities.pagination.OrderType.DESC.getOrder())
        );
    }

}