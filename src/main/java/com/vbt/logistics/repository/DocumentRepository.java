package com.vbt.logistics.repository;

import com.vbt.logistics.entity.Document;
import com.vbt.logistics.enums.EntityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Page<Document> findByRelatedTypeAndRelatedId(EntityType relatedType, Long relatedId, Pageable pageable);
}
