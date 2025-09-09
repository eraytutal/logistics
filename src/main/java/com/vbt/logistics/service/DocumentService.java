package com.vbt.logistics.service;

import com.vbt.logistics.dto.DocumentDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.enums.EntityType;
import org.springframework.data.domain.Pageable;

public interface DocumentService {
    PageResponseDto<DocumentDto> list(EntityType relatedType, Long relatedId, Pageable pageable);
}
