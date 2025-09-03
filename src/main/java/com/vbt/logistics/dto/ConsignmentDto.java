package com.vbt.logistics.dto;

import java.time.Instant;

public record ConsignmentDto(
        Long id,
        Instant createdAt
) {}
