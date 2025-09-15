package com.vbt.logistics.exception.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Repeatable;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = TimeOrderValidator.class)
@Repeatable(TimeOrder.List.class) // <-- EKLENDİ
public @interface TimeOrder {
    String message() default "start must be before or equal to end";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String start(); // alan adı

    String end();   // alan adı

    // Container annotation (repeatable için şart)
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        TimeOrder[] value();
    }
}
