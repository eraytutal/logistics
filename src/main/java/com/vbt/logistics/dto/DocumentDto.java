package com.vbt.logistics.dto;

import com.vbt.logistics.enums.EntityType;

import java.time.Instant;

public record DocumentDto(
        Long id,
        String docType,
        String fileUrl,
        EntityType relatedType,
        Long relatedId,
        Instant uploadedAt
) {}
