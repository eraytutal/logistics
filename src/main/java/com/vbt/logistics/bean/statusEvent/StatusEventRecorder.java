package com.vbt.logistics.bean.statusEvent;

import com.vbt.logistics.entity.StatusEvent;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.repository.StatusEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StatusEventRecorder {
    private final StatusEventRepository statusEventRepository;

    @Transactional
    public StatusEvent record(EntityType type, Long entityId, String status, String note) {
        StatusEvent e = StatusEvent.builder()
                .entityType(type)
                .entityId(entityId)
                .status(status)
                .note(note)
                .build();
        return statusEventRepository.save(e);
    }
}
