package com.luizmatias.findadev.api.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserRoleValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRole {
    String message() default "Invalid user role";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}