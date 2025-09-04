package com.vbt.logistics.dto;

import java.time.Instant;

public record UpdateShipmentLegActualRequestDto(
        Instant actualStart,
        Instant actualEnd
) {}
