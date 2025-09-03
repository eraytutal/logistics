package com.vbt.logistics.dto;

import com.vbt.logistics.enums.StopRole;

import java.time.Instant;

public record OrderStopDto(
        Long id,
        Long orderId,
        StopRole role,
        Long locationId,
        Instant windowStart,
        Instant windowEnd,
        Integer seq

) {}
