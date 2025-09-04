package com.vbt.logistics.repository;

import com.vbt.logistics.entity.ShipmentConsignment;
import com.vbt.logistics.entity.ShipmentConsignmentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentConsignmentRepository extends JpaRepository<ShipmentConsignment, ShipmentConsignmentId> {
    List<ShipmentConsignment> findByShipmentId(Long shipmentId);
    boolean existsByShipmentIdAndConsignmentId(Long shipmentId, Long consignmentId);
}
