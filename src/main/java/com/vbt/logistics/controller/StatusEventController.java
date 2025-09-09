package com.vbt.logistics.controller;

import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.StatusEventDto;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.service.StatusEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusEventController {

    private final StatusEventService service;

    @GetMapping("/status-events")
    public PageResponseDto<StatusEventDto> listByQuery(
            @RequestParam EntityType entityType,
            @RequestParam Long entityId,
            @SortDefault(sort = "occurredAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.list(entityType, entityId, pageable);
    }

    @GetMapping("/orders/{orderId}/events")
    public PageResponseDto<StatusEventDto> listOrderEvents(
            @PathVariable Long orderId,
            @SortDefault(sort = "occurredAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.list(EntityType.ORDER, orderId, pageable);
    }

    @GetMapping("/shipments/{shipmentId}/events")
    public PageResponseDto<StatusEventDto> listShipmentEvents(
            @PathVariable Long shipmentId,
            @SortDefault(sort = "occurredAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.list(EntityType.SHIPMENT, shipmentId, pageable);
    }
}
