package com.luizmatias.mycupid.api.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserType {
    String message() default "Invalid user type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}