package com.vbt.logistics.repository.specification;


import com.vbt.logistics.dto.LocationFilterDto;
import com.vbt.logistics.entity.Location;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class LocationSpecifications {
    private LocationSpecifications() {
    }

    public static Specification<Location> byFilter(LocationFilterDto f) {
        return (root, cq, cb) -> {
            List<Predicate> ands = new ArrayList<>();

            if (f != null) {
                if (hasText(f.getQ())) {
                    String q = "%" + f.getQ().toLowerCase(Locale.ROOT).trim() + "%";
                    Predicate any = cb.or(
                            cb.like(cb.lower(root.get("name")), q),
                            cb.like(cb.lower(root.get("city")), q),
                            cb.like(cb.lower(root.get("addressLine")), q),
                            cb.like(cb.lower(root.get("postalCode")), q),
                            cb.like(cb.lower(root.get("country")), q)
                    );
                    ands.add(any);
                }

                if (hasText(f.getCity())) {
                    ands.add(cb.equal(cb.lower(root.get("city")), f.getCity().toLowerCase(Locale.ROOT)));
                }
                if (hasText(f.getCountry())) {
                    ands.add(cb.equal(cb.lower(root.get("country")), f.getCountry().toLowerCase(Locale.ROOT)));
                }
                if (hasText(f.getPostalCode())) {
                    ands.add(cb.equal(cb.lower(root.get("postalCode")), f.getPostalCode().toLowerCase(Locale.ROOT)));
                }
            }

            return ands.isEmpty() ? cb.conjunction() : cb.and(ands.toArray(Predicate[]::new));
        };
    }

    private static boolean hasText(String s) {
        return s != null && !s.isBlank();
    }
}

