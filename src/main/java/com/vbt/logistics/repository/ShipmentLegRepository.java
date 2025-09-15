package com.vbt.logistics.repository;

import com.vbt.logistics.entity.ShipmentLeg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ShipmentLegRepository
        extends JpaRepository<ShipmentLeg, Long>, JpaSpecificationExecutor<ShipmentLeg> {

    @EntityGraph(attributePaths = {"startLocation", "endLocation", "vehicle", "driver", "carrier"})
    @NonNull
    Page<ShipmentLeg> findByShipmentId(@NonNull Long shipmentId, @NonNull Pageable pageable);

    @EntityGraph(attributePaths = {"startLocation", "endLocation", "vehicle", "driver", "carrier"})
    @NonNull
    Page<ShipmentLeg> findAll(@Nullable Specification<ShipmentLeg> spec, @NonNull Pageable pageable);

    boolean existsByShipmentIdAndSeq(@NonNull Long shipmentId, @NonNull Integer seq);

    // Buraya tekrar bakıcam.
    @SuppressWarnings("unused")
    @NonNull
    List<ShipmentLeg> findByShipmentId(@NonNull Long shipmentId, @NonNull Sort sort);
}
