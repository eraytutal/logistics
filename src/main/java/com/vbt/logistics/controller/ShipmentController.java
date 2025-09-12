package com.vbt.logistics.controller;

import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipments")
public class  ShipmentController {

    private final ShipmentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShipmentDto create(@RequestBody CreateShipmentRequestDto req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public ShipmentDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("/{shipmentId}/consignments")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ShipmentConsignmentDto> attach(
            @PathVariable Long shipmentId, @Valid @RequestBody AttachConsignmentsRequestDto req) {
        return service.attachConsignments(shipmentId, req);
    }

    // >>> YENİ: pageable + default sort
    @GetMapping("/{shipmentId}/consignments")
    public PageResponseDto<ShipmentConsignmentDto> listConsignments(
            @PathVariable Long shipmentId,
            @PageableDefault(size = 20)
            @SortDefault(sort = "id.consignmentId", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return service.listConsignments(shipmentId, pageable);
    }

    @PostMapping("/{shipmentId}/legs")
    @ResponseStatus(HttpStatus.CREATED)
    public ShipmentLegDto addLeg(@PathVariable Long shipmentId, @Valid @RequestBody AddShipmentLegRequestDto req) {
        return service.addLeg(shipmentId, req);
    }

    // >>> YENİ: pageable + default sort by seq asc
    @GetMapping("/{shipmentId}/legs")
    public PageResponseDto<ShipmentLegDto> listLegs(
            @PathVariable Long shipmentId,
            @PageableDefault(size = 20)
            @SortDefault(sort = "seq", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return service.listLegs(shipmentId, pageable);
    }

    @PatchMapping("/legs/{legId}/actuals")
    public ShipmentLegDto updateActual(@PathVariable Long legId,
                                       @RequestBody UpdateShipmentLegActualRequestDto req) {
        return service.updateLegActual(legId, req);
    }
}
