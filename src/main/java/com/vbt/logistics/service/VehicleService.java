package com.vbt.logistics.service;

import com.vbt.logistics.dto.*;
import org.springframework.data.domain.Pageable;

public interface VehicleService {
    VehicleDto create(CreateVehicleRequestDto req);
    VehicleDto get(Long id);
    VehicleDto update(Long id, UpdateVehicleRequestDto req);
    void delete(Long id);
    PageResponseDto<VehicleDto> list(String q, Pageable pageable);
}
