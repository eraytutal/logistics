package com.vbt.logistics.repository;

import com.vbt.logistics.entity.ShipmentConsignment;
import com.vbt.logistics.entity.ShipmentConsignmentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentConsignmentRepository
        extends JpaRepository<ShipmentConsignment, ShipmentConsignmentId> {

    List<ShipmentConsignment> findById_ShipmentId(Long shipmentId);

    Page<ShipmentConsignment> findById_ShipmentId(Long shipmentId, Pageable pageable);

    boolean existsById_ShipmentIdAndId_ConsignmentId(Long shipmentId, Long consignmentId);
}

