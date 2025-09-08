package com.vbt.logistics.repository;

import com.vbt.logistics.entity.ShipmentLeg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ShipmentLegRepository extends JpaRepository<ShipmentLeg, Long> {


    // Paging + sort (Controller’dan Pageable geliyor) → Ana yol
    @EntityGraph(attributePaths = {"startLocation", "endLocation", "vehicle", "driver", "carrier"})
    Page<ShipmentLeg> findByShipmentId(Long shipmentId, Pageable pageable);

    // İleride Specification ile karmaşık filtre/sıralama yaparken de aynı fetch graph’ı kullanabilmek için:
    @EntityGraph(attributePaths = {"startLocation", "endLocation", "vehicle", "driver", "carrier"})
    Page<ShipmentLeg> findAll(Specification<ShipmentLeg> spec, Pageable pageable);

    boolean existsByShipmentIdAndSeq(Long shipmentId, Integer seq);

    List<ShipmentLeg> findByShipmentId(Long shipmentId, Sort sort);
}
