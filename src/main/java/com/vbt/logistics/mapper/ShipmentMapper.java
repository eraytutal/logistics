package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.*;
import com.vbt.logistics.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapper {

    public ShipmentDto mapShipment(Shipment s) {
        return new ShipmentDto(
                s.getId(),
                s.getCreatedAt(),
                s.getReferenceNo()
        );
    }

    public ShipmentLegDto mapLeg(ShipmentLeg l) {
        return new ShipmentLegDto(
                l.getId(),
                l.getShipment() != null ? l.getShipment().getId() : null,
                l.getSeq(),
                l.getMode(),
                l.getVehicle() != null ? l.getVehicle().getId() : null,
                l.getDriver() != null ? l.getDriver().getId() : null,
                l.getCarrier() != null ? l.getCarrier().getId() : null,
                l.getBookingRef(),
                l.getAwbNo(),
                l.getBlNo(),
                l.getCmrNo(),
                l.getStartLocation() != null ? l.getStartLocation().getId() : null,
                l.getEndLocation() != null ? l.getEndLocation().getId() : null,
                l.getPlannedStart(),
                l.getPlannedEnd(),
                l.getActualStart(),
                l.getActualEnd()
        );
    }

    public ShipmentConsignmentDto mapShipmentConsignment(ShipmentConsignment sc) {
        return new ShipmentConsignmentDto(
                sc.getShipment() != null ? sc.getShipment().getId() : null,
                sc.getConsignment() != null ? sc.getConsignment().getId() : null
        );
    }
}
