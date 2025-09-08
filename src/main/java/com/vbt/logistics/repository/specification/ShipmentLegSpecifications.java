package com.vbt.logistics.repository.specification;

import com.vbt.logistics.entity.ShipmentLeg;
import com.vbt.logistics.enums.Mode;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public final class ShipmentLegSpecifications {

    private ShipmentLegSpecifications() {
    }

    public static Specification<ShipmentLeg> hasShipmentId(Long shipmentId) {
        return (root, cq, cb) -> cb.equal(root.get("shipment").get("id"), shipmentId);
    }

    public static Specification<ShipmentLeg> modeIs(Mode mode) {
        return (root, cq, cb) -> mode == null ? cb.conjunction() : cb.equal(root.get("mode"), mode);
    }

    public static Specification<ShipmentLeg> carrierIdIs(Long carrierId) {
        return (root, cq, cb) -> carrierId == null ? cb.conjunction() :
                cb.equal(root.get("carrier").get("id"), carrierId);
    }

    public static Specification<ShipmentLeg> plannedBetween(Instant from, Instant to) {
        return (root, cq, cb) -> {
            if (from == null && to == null) return cb.conjunction();
            if (from != null && to != null) return cb.between(root.get("plannedStart"), from, to);
            if (from != null) return cb.greaterThanOrEqualTo(root.get("plannedStart"), from);
            return cb.lessThanOrEqualTo(root.get("plannedStart"), to);
        };
    }

    // Örnek: startLocation.country filtresi
    public static Specification<ShipmentLeg> startCountryIs(String country) {
        return (root, cq, cb) -> {
            if (country == null || country.isBlank()) return cb.conjunction();
            // join (fetch yok; fetch sayfalama count query’lerinde sorun çıkarır)
            var sl = root.join("startLocation", JoinType.LEFT);
            return cb.equal(sl.get("country"), country);
        };
    }
}
