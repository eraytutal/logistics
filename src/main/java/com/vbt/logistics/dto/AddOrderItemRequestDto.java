package com.vbt.logistics.dto;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record AddOrderItemRequestDto(
        String description,
        @DecimalMin(value = "0.0", inclusive = true) BigDecimal weightKg,
        @DecimalMin(value = "0.0", inclusive = true) BigDecimal volumeM3,
        boolean hazardFlag,
        boolean tempControl
) {}
