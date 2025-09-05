package com.vbt.logistics.dto;


import jakarta.validation.constraints.Size;
import java.time.Instant;

public record CreateOrderRequestDto(
        @Size(max = 2000) String specialNotes,
        Instant createdAt
) {}
