package com.vbt.logistics.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vbt.logistics.util.BigDecimalScale3Serializer;

import java.math.BigDecimal;

public record ConsignmentItemDto(
        Long consignmentId,
        Long orderItemId,
        @JsonSerialize(using = BigDecimalScale3Serializer.class) BigDecimal weightKg,
        @JsonSerialize(using = BigDecimalScale3Serializer.class) BigDecimal volumeM3
) {}
