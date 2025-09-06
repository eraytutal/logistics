package com.vbt.logistics.repository;

import com.vbt.logistics.entity.ShipmentLeg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentLegRepository extends JpaRepository<ShipmentLeg,Long> {
    List<ShipmentLeg> findByShipmentIdOrderBySeqAsc(Long shipmentId);
    boolean existsByShipmentIdAndSeq(Long shipmentId, Integer seq);

    Page<ShipmentLeg> findByShipmentId(Long shipmentId, Pageable pageable);
}
