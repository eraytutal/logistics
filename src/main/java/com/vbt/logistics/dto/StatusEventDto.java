package com.vbt.logistics.dto;

import com.vbt.logistics.enums.EntityType;

import java.time.Instant;

public record StatusEventDto(
        Long id,
        EntityType entityType,
        Long entityId,
        String status,
        Instant occurredAt,
        String note
) {}
