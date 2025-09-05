package com.vbt.logistics.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record AddOrderItemRequestDto(
        @Size(max = 4000) String description,

        @PositiveOrZero
        @Digits(integer = 9, fraction = 3)
        BigDecimal weightKg,

        @PositiveOrZero
        @Digits(integer = 9, fraction = 3)
        BigDecimal volumeM3,

        boolean hazardFlag,
        boolean tempControl
) {}
