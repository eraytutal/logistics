package com.vbt.logistics.dto;

import java.time.Instant;

public record OrderDto(
        Long id,
        Instant createdAt,
        String specialNotes
) {}
