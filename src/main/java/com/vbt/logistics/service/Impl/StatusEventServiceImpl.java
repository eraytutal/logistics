package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.statusEvent.ListStatusEventsBean;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.StatusEventDto;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.service.StatusEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusEventServiceImpl implements StatusEventService {
    private final ListStatusEventsBean listBean;

    @Override
    public PageResponseDto<StatusEventDto> list(EntityType entityType, Long entityId, Pageable pageable) {
        return listBean.list(entityType, entityId, pageable);
    }
}
