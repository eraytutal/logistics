package com.vbt.logistics.controller;

import com.vbt.logistics.dto.LocationDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.CreateLocationRequestDto;
import com.vbt.logistics.dto.LocationFilterDto;
import com.vbt.logistics.dto.UpdateLocationRequestDto;
import com.vbt.logistics.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto create(@Valid @RequestBody CreateLocationRequestDto req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public LocationDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PatchMapping("/{id}")
    public LocationDto update(@PathVariable Long id, @RequestBody UpdateLocationRequestDto req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public PageResponseDto<LocationDto> list(
            @org.springframework.web.bind.annotation.ModelAttribute
            @org.springdoc.core.annotations.ParameterObject
            LocationFilterDto filter,

            @org.springdoc.core.annotations.ParameterObject
            @org.springframework.data.web.SortDefault(sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return service.list(filter, pageable);
    }
}

