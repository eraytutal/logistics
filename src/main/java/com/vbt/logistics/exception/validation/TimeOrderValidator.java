package com.vbt.logistics.exception.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.RecordComponent;
import java.lang.reflect.Field;
import java.time.Instant;

public class TimeOrderValidator implements ConstraintValidator<TimeOrder, Object> {
    private String startField;
    private String endField;

    @Override
    public void initialize(TimeOrder ann) {
        this.startField = ann.start();
        this.endField = ann.end();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        if (value == null) return true;

        Instant start = extractInstant(value, startField);
        Instant end   = extractInstant(value, endField);

        // İkisi de null ise ya da biri null ise: iş kuralı değildir → geçerli
        if (start == null || end == null) return true;

        // start <= end olmalı
        return !start.isAfter(end);
    }

    private Instant extractInstant(Object obj, String fieldName) {
        try {
            // record ise…
            if (obj.getClass().isRecord()) {
                for (RecordComponent rc : obj.getClass().getRecordComponents()) {
                    if (rc.getName().equals(fieldName)) {
                        Object v = rc.getAccessor().invoke(obj);
                        return (v instanceof Instant) ? (Instant) v : null;
                    }
                }
                return null;
            }
            // normal class ise…
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            Object v = f.get(obj);
            return (v instanceof Instant) ? (Instant) v : null;
        } catch (Throwable ignore) {
            return null; // alan yoksa valid say (fail-closed yerine fail-open tercih)
        }
    }
}

