package com.vbt.logistics.controller;

import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.dto.ConsignmentItemDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.service.ConsignmentService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
}
