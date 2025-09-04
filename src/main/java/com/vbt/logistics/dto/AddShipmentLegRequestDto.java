package com.vbt.logistics.dto;

import com.vbt.logistics.enums.Mode;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record AddShipmentLegRequestDto(
        @NotNull Integer seq,
        @NotNull Mode mode,
        @NotNull Long startLocationId,
        @NotNull Long endLocationId,
        Instant plannedStart,
        Instant plannedEnd,

        // ROAD (kendi filomuz)
        Long vehicleId,
        Long driverId,

        // Dış taşıyıcı / AIR / SEA
        Long carrierId,
        String bookingRef,
        String awbNo,
        String blNo,
        String cmrNo
) {
}
