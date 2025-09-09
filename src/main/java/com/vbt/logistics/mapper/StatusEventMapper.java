package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.StatusEventDto;
import com.vbt.logistics.entity.StatusEvent;
import org.springframework.stereotype.Component;

@Component
public class StatusEventMapper {
    public StatusEventDto map(StatusEvent e) {
        return new StatusEventDto(
                e.getId(),
                e.getEntityType(),
                e.getEntityId(),
                e.getStatus(),
                e.getOccurredAt(),
                e.getNote()
        );
    }
}
