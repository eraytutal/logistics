package com.vbt.logistics.dto;

public record CarrierDto(
        Long id,
        String name,
        String contact,
        Long locationId
) {}
