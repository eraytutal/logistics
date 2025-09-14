package com.vbt.logistics.service;

import com.vbt.logistics.dto.*;
import org.springframework.data.domain.Pageable;

public interface DriverService {
    DriverDto create(CreateDriverRequestDto req);

    DriverDto get(Long id);

    DriverDto update(Long id, UpdateDriverRequestDto req);

    void delete(Long id);

    PageResponseDto<DriverDto> list(String q, Pageable pageable);
}

