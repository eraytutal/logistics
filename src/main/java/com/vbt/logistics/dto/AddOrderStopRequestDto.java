package com.vbt.logistics.dto;



import com.vbt.logistics.enums.StopRole;
import com.vbt.logistics.exception.validation.TimeOrder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@TimeOrder(start = "windowStart", end = "windowEnd",
        message = "windowStart must be before or equal to windowEnd")
public record AddOrderStopRequestDto(
        @NotNull StopRole role,
        @NotNull Long locationId,
        Instant windowStart,
        Instant windowEnd,
        @NotNull @Min(1) Integer seq
) {}
