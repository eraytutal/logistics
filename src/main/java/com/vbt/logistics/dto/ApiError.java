package com.vbt.logistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;

@Getter
@Builder
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;              // ör: 2025-09-04T19:14:00Z
    private int status;                     // 404, 400, 409, ...
    private String error;                   // Not Found, Bad Request, Conflict, ...
    private String message;                 // iş mesajı (kısa ve faydalı)
    private String path;                    // /api/v1/orders/1/stops
    private String code;                    // (opsiyonel) uygulama içi hata kodu
    private Map<String, String> validation; // (opsiyonel) alan -> mesaj
}

