package com.vbt.logistics.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record UpdateLocationRequestDto(
        String name,
        String addressLine,
        String city,
        String country,
        String postalCode,
        @DecimalMin(value="-90")  @DecimalMax(value="90")  Double latitude,
        @DecimalMin(value="-180") @DecimalMax(value="180") Double longitude
) {}
