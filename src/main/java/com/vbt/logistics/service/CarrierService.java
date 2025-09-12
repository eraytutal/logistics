package com.vbt.logistics.service;

import com.vbt.logistics.dto.*;
import org.springframework.data.domain.Pageable;

public interface CarrierService {
    CarrierDto create(CreateCarrierRequestDto req);
    CarrierDto get(Long id);
    CarrierDto update(Long id, UpdateCarrierRequestDto req);
    void delete(Long id);
    PageResponseDto<CarrierDto> list(String q, Pageable pageable);
}
