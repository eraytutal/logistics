package com.vbt.logistics.dto;

import com.vbt.logistics.enums.StopRole;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record AddOrderStopRequestDto(
        @NotNull StopRole role,
        @NotNull Long locationId,
        Instant windowStart,
        Instant windowEnd,
        @NotNull Integer seq
) {}
