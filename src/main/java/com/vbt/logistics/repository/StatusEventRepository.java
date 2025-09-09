package com.vbt.logistics.repository;

import com.vbt.logistics.entity.StatusEvent;
import com.vbt.logistics.enums.EntityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusEventRepository extends JpaRepository<StatusEvent, Long> {
    Page<StatusEvent> findByEntityTypeAndEntityIdOrderByOccurredAtDesc(EntityType entityType,
                                                                        Long entityId,
                                                                        Pageable pageable
    );
}
