package com.vbt.logistics.repository;

import com.vbt.logistics.entity.ConsignmentItem;
import com.vbt.logistics.entity.ConsignmentItemId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsignmentItemRepository extends JpaRepository<ConsignmentItem, ConsignmentItemId> {
    Page<ConsignmentItem> findById_ConsignmentId(Long consignmentId, Pageable pageable);
}
