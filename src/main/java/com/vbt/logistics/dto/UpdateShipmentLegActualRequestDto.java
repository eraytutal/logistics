package com.vbt.logistics.dto;

import com.vbt.logistics.exception.validation.TimeOrder;

import java.time.Instant;

@TimeOrder(start = "actualStart", end = "actualEnd",
        message = "actualStart must be before or equal to actualEnd")
public record UpdateShipmentLegActualRequestDto(
        Instant actualStart,
        Instant actualEnd
) {}

