package com.vbt.logistics.dto;

import java.time.Instant;

public record CreateOrderRequestDto(
        String specialNotes,
        Instant createdAt
) {}
