package com.vbt.logistics.exception.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

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
        Instant end = extractInstant(value, endField);

        if (start == null || end == null) return true;

        boolean ok = !start.isAfter(end);
        if (!ok) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(startField)
                    .addConstraintViolation();
        }
        return ok;
    }

    private Instant toInstant(Object v) {
        return switch (v) {
            case Instant i -> i;
            case OffsetDateTime odt -> odt.toInstant();
            case ZonedDateTime zdt -> zdt.toInstant();
            case LocalDateTime ldt -> ldt.atZone(java.time.ZoneOffset.UTC).toInstant();
            case java.time.LocalDate ld -> ld.atStartOfDay(java.time.ZoneOffset.UTC).toInstant();
            case java.util.Date d -> d.toInstant();
            case null, default -> null;
        };
    }

    private Instant extractInstant(Object obj, String fieldName) {
        try {
            if (obj.getClass().isRecord()) {
                for (RecordComponent rc : obj.getClass().getRecordComponents()) {
                    if (rc.getName().equals(fieldName)) {
                        Object v = rc.getAccessor().invoke(obj);
                        return toInstant(v);
                    }
                }
                return null;
            }

            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            Object v = f.get(obj);
            return toInstant(v);
        } catch (Throwable ignore) {
            return null;
        }
    }
}
