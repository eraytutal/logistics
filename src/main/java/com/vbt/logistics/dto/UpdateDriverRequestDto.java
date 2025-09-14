package com.vbt.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateDriverRequestDto(
        @NotBlank @Size(max = 120) String fullName,
        @NotBlank @Size(max = 40)  String licenseNo,
        @Size(max = 40)            String phone
) {}
