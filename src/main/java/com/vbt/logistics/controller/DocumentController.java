package com.vbt.logistics.controller;

import com.vbt.logistics.dto.DocumentDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DocumentController {

    private final DocumentService service;

    @GetMapping("/documents")
    public PageResponseDto<DocumentDto> list(
            @RequestParam EntityType relatedType,
            @RequestParam Long relatedId,
            @ParameterObject
            @SortDefault(sort = "uploadedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.list(relatedType, relatedId, pageable);
    }

    @GetMapping("/orders/{orderId}/documents")
    public PageResponseDto<DocumentDto> listOrderDocs(
            @PathVariable Long orderId,
            @ParameterObject
            @SortDefault(sort = "uploadedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.list(EntityType.ORDER, orderId, pageable);
    }

    @GetMapping("/shipments/{shipmentId}/documents")
    public PageResponseDto<DocumentDto> listShipmentDocs(
            @PathVariable Long shipmentId,
            @ParameterObject
            @SortDefault(sort = "uploadedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.list(EntityType.SHIPMENT, shipmentId, pageable);
    }
}
