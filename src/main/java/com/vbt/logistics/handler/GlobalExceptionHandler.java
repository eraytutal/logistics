package com.vbt.logistics.handler;

import com.vbt.logistics.dto.ApiError;
import com.vbt.logistics.exception.ConflictException;
import com.vbt.logistics.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* 404 — bulunamadı */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req, "NOT_FOUND");
    }

    /* 400 — kötü istek (iş kuralı/parametre hatası) */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArg(IllegalArgumentException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req, "BAD_REQUEST");
    }

    /* 400 — Bean Validation (DTO üzerinde @NotNull, @DecimalMin vs.) */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> v = new LinkedHashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            v.put(fe.getField(), fe.getDefaultMessage());
        }
        ApiError body = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .path(req.getRequestURI())
                .code("VALIDATION_ERROR")
                .validation(v)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /* 400 — Query param / path param üzerindeki ConstraintViolation'lar */
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class) // <- YENİ
    public ResponseEntity<ApiError> handleConstraintViolations(
            jakarta.validation.ConstraintViolationException ex, HttpServletRequest req) {
        Map<String,String> v = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(cv -> v.put(
                cv.getPropertyPath().toString(), cv.getMessage()
        ));
        ApiError body = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .path(req.getRequestURI())
                .code("VALIDATION_ERROR")
                .validation(v)
                .build();
        return ResponseEntity.badRequest().body(body);
    }

    /* 409 — DB constraint’leri (unique, FK, not-null vs.) */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        String code = "DATA_INTEGRITY";

        Throwable root = rootCause(ex);
        if (root instanceof ConstraintViolationException hEx && hEx.getSQLException() != null) {
            String sqlState = hEx.getSQLException().getSQLState();
            if ("23505".equals(sqlState)) code = "UNIQUE_VIOLATION";
            else if ("23503".equals(sqlState)) code = "FOREIGN_KEY_VIOLATION";
            else if ("23502".equals(sqlState)) code = "NOT_NULL_VIOLATION";
        } else if (root instanceof SQLException sql) {
            String sqlState = sql.getSQLState();
            if ("23505".equals(sqlState)) code = "UNIQUE_VIOLATION";
            else if ("23503".equals(sqlState)) code = "FOREIGN_KEY_VIOLATION";
            else if ("23502".equals(sqlState)) code = "NOT_NULL_VIOLATION";
        }

        String msg = safeMessage(ex.getMostSpecificCause());
        return build(HttpStatus.CONFLICT, msg, req, code);
    }

    /* 400 — JPA/Bean validation (entity seviyesinde) */
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ApiError> handleTxSystem(TransactionSystemException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, safeMessage(ex), req, "TX_VALIDATION");
    }

    /* 409 — Domain spesifik çatışmalar */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflict(ConflictException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, ex.getMessage(), req, "CONFLICT");
    }

    /* 400 — Bozuk JSON gövdesi (path/tip detayını mümkünse ekle) */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleBadJson(HttpMessageNotReadableException ex, HttpServletRequest req) {
        Map<String, String> v = new LinkedHashMap<>();

        // Jackson InvalidFormatException ise path & beklenen tipi ekle
        Throwable root = rootCause(ex);
        try {
            Class<?> ifeClass = Class.forName("com.fasterxml.jackson.databind.exc.InvalidFormatException");
            if (ifeClass.isInstance(root)) {
                Object ife = ifeClass.cast(root);
                var getPath = ifeClass.getMethod("getPath");
                var getTargetType = ifeClass.getMethod("getTargetType");

                var pathRefs = (java.util.List<?>) getPath.invoke(ife);
                String path = pathRefs.stream()
                        .map(ref -> {
                            try {
                                var m = ref.getClass().getMethod("getFieldName");
                                Object fn = m.invoke(ref);
                                return fn == null ? "?" : fn.toString();
                            } catch (Exception e) { return "?"; }
                        })
                        .collect(Collectors.joining("."));

                Class<?> targetType = (Class<?>) getTargetType.invoke(ife);
                v.put(path, "Expected type: " + (targetType != null ? targetType.getSimpleName() : "unknown"));
            }
        } catch (Throwable ignore) {
            // Jackson sınıfları yoksa/yordam başarısızsa sessizce geç
        }

        ApiError body = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Malformed JSON request")
                .path(req.getRequestURI())
                .code("BAD_JSON")
                .validation(v.isEmpty() ? null : v)
                .build();
        return ResponseEntity.badRequest().body(body);
    }

    /* 400 — Tip uyumsuzluğu (ör. ?entityType=FOO) */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                       HttpServletRequest req) {
        String msg = "Invalid parameter '%s': expected %s".formatted(
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown"
        );
        return build(HttpStatus.BAD_REQUEST, msg, req, "TYPE_MISMATCH");
    }

    /* 400 — Eksik/yanlış query param binding */
    @ExceptionHandler({ BindException.class, MissingServletRequestParameterException.class })
    public ResponseEntity<ApiError> handleBadParams(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "Malformed query parameter(s)", req, "BAD_PARAM");
    }

    /* 500 — yakalanmayan diğerleri */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest req) {
        log.error("Unhandled exception at {}: {}", req.getRequestURI(), ex.toString(), ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", req, "UNEXPECTED_ERROR");
    }

    /* ---- helpers ---- */

    private ResponseEntity<ApiError> build(HttpStatus status, String message, HttpServletRequest req, String code) {
        ApiError body = ApiError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(req.getRequestURI())
                .code(code)
                .build();
        return ResponseEntity.status(status).body(body);
    }

    private static Throwable rootCause(Throwable t) {
        Throwable r = t;
        while (r.getCause() != null && r.getCause() != r) r = r.getCause();
        return r;
    }

    private static String safeMessage(Throwable t) {
        if (t == null) return null;
        String m = t.getMessage();
        return (m != null && m.length() > 500) ? m.substring(0, 500) + "..." : m;
    }
}
