package com.luizmatias.findadev.api.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderType {
    String message() default "Invalid order type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}