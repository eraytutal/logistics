package com.vbt.logistics.service;

import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.StatusEventDto;
import com.vbt.logistics.enums.EntityType;
import org.springframework.data.domain.Pageable;

public interface StatusEventService {
    PageResponseDto<StatusEventDto> list(EntityType entityType, Long entityId, Pageable pageable);
}

