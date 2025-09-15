package com.vbt.logistics.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AddConsignmentItemRequestDto(
        @NotNull Long orderItemId,
        @DecimalMin(value = "0.0") BigDecimal weightKg,
        @DecimalMin(value = "0.0") BigDecimal volumeM3
) {
}
