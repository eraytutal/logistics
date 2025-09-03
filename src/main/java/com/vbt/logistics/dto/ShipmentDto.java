package com.vbt.logistics.dto;

import java.time.Instant;

public record ShipmentDto(
        Long id,
        Instant createdAt,
        String referenceNo
) {}
