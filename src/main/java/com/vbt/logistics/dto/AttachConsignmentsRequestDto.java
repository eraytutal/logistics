package com.vbt.logistics.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AttachConsignmentsRequestDto(
        @NotEmpty List<Long> consignmentIds
) {}
