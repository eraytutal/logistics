package com.vbt.logistics.repository.specification;

import com.vbt.logistics.entity.ShipmentLeg;
import com.vbt.logistics.enums.Mode;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public final class ShipmentLegSpecifications {

    private ShipmentLegSpecifications() {
    }

    /* Temel: shipmentId */
    public static Specification<ShipmentLeg> hasShipmentId(Long shipmentId) {
        return (root, cq, cb) -> shipmentId == null
                ? cb.conjunction()
                : cb.equal(root.get("shipment").get("id"), shipmentId);
    }

    /* Mode filtresi */
    public static Specification<ShipmentLeg> modeIs(Mode mode) {
        return (root, cq, cb) -> mode == null
                ? cb.conjunction()
                : cb.equal(root.get("mode"), mode);
    }

    /* Carrier/Vehicle/Driver */
    public static Specification<ShipmentLeg> carrierIdIs(Long carrierId) {
        return (root, cq, cb) -> carrierId == null
                ? cb.conjunction()
                : cb.equal(root.get("carrier").get("id"), carrierId);
    }

    public static Specification<ShipmentLeg> vehicleIdIs(Long vehicleId) {
        return (root, cq, cb) -> vehicleId == null
                ? cb.conjunction()
                : cb.equal(root.get("vehicle").get("id"), vehicleId);
    }

    public static Specification<ShipmentLeg> driverIdIs(Long driverId) {
        return (root, cq, cb) -> driverId == null
                ? cb.conjunction()
                : cb.equal(root.get("driver").get("id"), driverId);
    }

    /* Location id’ler */
    public static Specification<ShipmentLeg> startLocationIdIs(Long id) {
        return (root, cq, cb) -> id == null
                ? cb.conjunction()
                : cb.equal(root.get("startLocation").get("id"), id);
    }

    public static Specification<ShipmentLeg> endLocationIdIs(Long id) {
        return (root, cq, cb) -> id == null
                ? cb.conjunction()
                : cb.equal(root.get("endLocation").get("id"), id);
    }

    /* planned aralığı — DÜZELTME: yalnızca 'to' varsa plannedEnd <= to */
    public static Specification<ShipmentLeg> plannedBetween(Instant from, Instant to) {
        return (root, cq, cb) -> {
            if (from == null && to == null) return cb.conjunction();
            if (from != null && to != null) return cb.between(root.get("plannedStart"), from, to);
            if (from != null) return cb.greaterThanOrEqualTo(root.get("plannedStart"), from);
            /* only to != null */
            return cb.lessThanOrEqualTo(root.get("plannedEnd"), to);
        };
    }

    /* actual aralığı */
    public static Specification<ShipmentLeg> actualBetween(Instant from, Instant to) {
        return (root, cq, cb) -> {
            if (from == null && to == null) return cb.conjunction();
            if (from != null && to != null) return cb.between(root.get("actualStart"), from, to);
            if (from != null) return cb.greaterThanOrEqualTo(root.get("actualStart"), from);
            /* only to != null */
            return cb.lessThanOrEqualTo(root.get("actualEnd"), to);
        };
    }

    /* Örnek: startLocation.country filtresi (sendeki gibi JOIN ile) */
    public static Specification<ShipmentLeg> startCountryIs(String country) {
        return (root, cq, cb) -> {
            if (country == null || country.isBlank()) return cb.conjunction();
            var sl = root.join("startLocation", JoinType.LEFT);
            return cb.equal(sl.get("country"), country);
        };
    }
}
