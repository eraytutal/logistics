package com.vbt.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record UpdateVehicleRequestDto(
        @NotBlank @Size(max = 20) String plateNumber,
        @PositiveOrZero Integer capacityKg,
        @PositiveOrZero Double capacityM3,
        @PositiveOrZero Integer palletCap,
        Boolean adrCompliant,
        Boolean coldChain
) {}
