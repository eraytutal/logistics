package com.vbt.logistics.dto;

import com.vbt.logistics.enums.Mode;

import java.time.Instant;

public record ShipmentLegDto(
        Long id,
        Long shipmentId,
        Integer seq,
        Mode mode,
        Long vehicleId,
        Long driverId,
        Long carrierId,
        String bookingRef,
        String awbNo,
        String blNo,
        String cmrNo,
        Long startLocationId,
        Long endLocationId,
        Instant plannedStart,
        Instant plannedEnd,
        Instant actualStart,
        Instant actualEnd
) {}
