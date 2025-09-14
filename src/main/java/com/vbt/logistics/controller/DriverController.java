package com.vbt.logistics.controller;

import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drivers")
public class DriverController {
    private final DriverService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDto create(@Valid @RequestBody CreateDriverRequestDto req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public DriverDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public DriverDto update(@PathVariable Long id, @Valid @RequestBody UpdateDriverRequestDto req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public PageResponseDto<DriverDto> list(
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20)
            @SortDefault(sort = "fullName", direction = Sort.Direction.ASC)
            @ParameterObject Pageable pageable) {
        return service.list(q, pageable);
    }
}

