package com.vbt.logistics.service;

import com.vbt.logistics.dto.LocationDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.CreateLocationRequestDto;
import com.vbt.logistics.dto.LocationFilterDto;
import com.vbt.logistics.dto.UpdateLocationRequestDto;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    LocationDto create(CreateLocationRequestDto req);

    LocationDto get(Long id);

    LocationDto update(Long id, UpdateLocationRequestDto req);

    void delete(Long id);

    PageResponseDto<LocationDto> list(LocationFilterDto filter, Pageable pageable);
}

