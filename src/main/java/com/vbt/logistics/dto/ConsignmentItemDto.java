package com.vbt.logistics.dto;

import java.math.BigDecimal;

public record ConsignmentItemDto(
        Long consignmentId,
        Long orderItemId,
        BigDecimal weightKg,
        BigDecimal volumeM3
) {}
