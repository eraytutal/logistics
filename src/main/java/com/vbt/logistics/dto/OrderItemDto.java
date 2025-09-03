package com.vbt.logistics.dto;

import java.math.BigDecimal;

public record OrderItemDto(
        Long id,
        Long orderId,
        String description,
        BigDecimal weightKg,
        BigDecimal volumeM3,
        boolean hazardFlag,
        boolean tempControl
) {}
