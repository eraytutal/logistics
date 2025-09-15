package com.vbt.logistics.controller;

import com.vbt.logistics.dto.AddConsignmentItemRequestDto;
import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.dto.ConsignmentItemDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.service.ConsignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/consignments")
public class ConsignmentController {

    private final ConsignmentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsignmentDto create() {  // şimdilik body yok
        return service.create();
    }

    @GetMapping("/{id}")
    public ConsignmentDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public PageResponseDto<ConsignmentDto> list(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return service.list(pageable);
    }

    @GetMapping("/{id}/items")
    public PageResponseDto<ConsignmentItemDto> listItems(
            @PathVariable Long id,
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return service.listItems(id, pageable);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ConsignmentItemDto> addItem(@PathVariable Long id,
                                                      @Valid @RequestBody AddConsignmentItemRequestDto req) {
        if (service.itemExists(id, req.orderItemId())) {
            return ResponseEntity.ok(service.getItem(id, req.orderItemId()));
        }
        var created = service.addItem(id, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}/items/{orderItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id, @PathVariable Long orderItemId) {
        service.deleteItem(id, orderItemId);
    }
}
