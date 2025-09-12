package com.vbt.logistics.controller;

import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.CarrierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carriers")
public class CarrierController {

    private final CarrierService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarrierDto create(@Valid @RequestBody CreateCarrierRequestDto req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public CarrierDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public CarrierDto update(@PathVariable Long id, @Valid @RequestBody UpdateCarrierRequestDto req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public PageResponseDto<CarrierDto> list(
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20)
            @SortDefault(sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return service.list(q, pageable);
    }
}
