package com.vbt.logistics.controller;

import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private final VehicleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleDto create(@Valid @RequestBody CreateVehicleRequestDto req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public VehicleDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public VehicleDto update(@PathVariable Long id, @Valid @RequestBody UpdateVehicleRequestDto req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public PageResponseDto<VehicleDto> list(
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20)
            @SortDefault(sort = "plateNumber", direction = Sort.Direction.ASC)
            @ParameterObject Pageable pageable) {
        return service.list(q, pageable);
    }
}
