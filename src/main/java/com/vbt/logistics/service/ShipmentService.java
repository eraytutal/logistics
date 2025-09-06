package com.vbt.logistics.service;

import com.vbt.logistics.dto.*;
import org.springframework.data.domain.Pageable;

public interface ShipmentService {
    ShipmentDto create(CreateShipmentRequestDto req);

    ShipmentDto get(Long id);

    java.util.List<ShipmentConsignmentDto> attachConsignments(Long shipmentId, AttachConsignmentsRequestDto req);

    PageResponseDto<ShipmentConsignmentDto> listConsignments(Long shipmentId, Pageable pageable);

    ShipmentLegDto addLeg(Long shipmentId, AddShipmentLegRequestDto req);

    PageResponseDto<ShipmentLegDto> listLegs(Long shipmentId, Pageable pageable);

    ShipmentLegDto updateLegActual(Long legId, UpdateShipmentLegActualRequestDto req);
}
