package com.vbt.logistics.dto;

import java.math.BigDecimal;

public record VehicleDto(
        Long id,
        String plateNumber,
        BigDecimal capacityKg,
        BigDecimal capacityM3,
        Integer palletCap,
        boolean adrCompliant,
        boolean coldChain
) {}
