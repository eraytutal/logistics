package com.vbt.logistics.dto;

public record LocationDto(
        Long id,
        String name,
        String addressLine,
        String city,
        String country,
        String postalCode,
        Double latitude,
        Double longitude
) {}
