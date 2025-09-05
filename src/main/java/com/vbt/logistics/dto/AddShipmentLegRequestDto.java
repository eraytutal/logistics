package com.vbt.logistics.dto;

import com.vbt.logistics.enums.Mode;
import com.vbt.logistics.exception.validation.TimeOrder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@TimeOrder(start = "plannedStart", end = "plannedEnd",
        message = "plannedStart must be before or equal to plannedEnd")
public record AddShipmentLegRequestDto(
        @NotNull @Min(1) Integer seq,
        @NotNull Mode mode,
        @NotNull Long startLocationId,
        @NotNull Long endLocationId,
        Instant plannedStart,
        Instant plannedEnd,
        Long vehicleId,
        Long driverId,
        Long carrierId,
        String bookingRef,
        String awbNo,
        String blNo,
        String cmrNo
) {}

