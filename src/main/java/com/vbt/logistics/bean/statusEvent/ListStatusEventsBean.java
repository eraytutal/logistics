package com.vbt.logistics.bean.statusEvent;

import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.StatusEventDto;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.mapper.StatusEventMapper;
import com.vbt.logistics.repository.StatusEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListStatusEventsBean {

    private final StatusEventRepository repo;
    private final StatusEventMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<StatusEventDto> list(EntityType type, Long entityId, Pageable pageable) {
        Page<StatusEventDto> page = repo
                .findByEntityTypeAndEntityIdOrderByOccurredAtDesc(type, entityId, pageable)
                .map(mapper::map);
        return PageResponseDto.from(page);
    }
}
