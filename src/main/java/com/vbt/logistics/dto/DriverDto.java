package com.vbt.logistics.dto;

public record DriverDto(
        Long id,
        String fullName,
        String licenseNo,
        String phone
) {}
