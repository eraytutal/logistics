package com.vbt.logistics.exception.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TimeOrderValidator.class)
public @interface TimeOrder {
    String message() default "start must be before or equal to end";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String start(); // alan adı
    String end();   // alan adı
}

