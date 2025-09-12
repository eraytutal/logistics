package com.vbt.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCarrierRequestDto(
        @NotBlank @Size(max = 150) String name,
        @Size(max = 150) String contact,
        Long locationId
) {}
