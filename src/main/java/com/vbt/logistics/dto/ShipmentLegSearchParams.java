package com.vbt.logistics.dto;

import com.vbt.logistics.enums.Mode;
import com.vbt.logistics.exception.validation.TimeOrder;

import java.time.Instant;

@TimeOrder(start = "plannedFrom", end = "plannedTo", message = "plannedFrom must be <= plannedTo")
@TimeOrder(start = "actualFrom",  end = "actualTo",  message = "actualFrom must be <= actualTo")
public record ShipmentLegSearchParams(
        Mode mode,
        Instant plannedFrom,
        Instant plannedTo,
        Instant actualFrom,
        Instant actualTo,
        Long carrierId,
        Long vehicleId,
        Long driverId,
        Long startLocationId,
        Long endLocationId
) {}

