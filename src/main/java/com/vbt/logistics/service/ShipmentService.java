package com.vbt.logistics.service;

import com.vbt.logistics.dto.*;

import java.util.List;

public interface ShipmentService {
    ShipmentDto create(CreateShipmentRequestDto req);
    ShipmentDto get(Long id);

    List<ShipmentConsignmentDto> attachConsignments(Long shipmentId, AttachConsignmentsRequestDto req);
    List<ShipmentConsignmentDto> listConsignments(Long shipmentId);

    ShipmentLegDto addLeg(Long shipmentId, AddShipmentLegRequestDto req);
    List<ShipmentLegDto> listLegs(Long shipmentId);
    ShipmentLegDto updateLegActual(Long legId, UpdateShipmentLegActualRequestDto req);
}
