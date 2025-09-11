package com.vbt.logistics.repository.specification;


import com.vbt.logistics.dto.LocationFilterDto;
import com.vbt.logistics.entity.Location;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public final class LocationSpecifications {
    private LocationSpecifications() {
    }

    public static Specification<Location> byFilter(LocationFilterDto f) {
        return (root, cq, cb) -> {
            var p = cb.conjunction();
            if (f == null) return p;

            if (hasText(f.getQ())) {
                String like = "%" + f.getQ().toLowerCase(Locale.ROOT) + "%";
                var nameLike = cb.like(cb.lower(root.get("name")), like);
                var addrLike = cb.like(cb.lower(root.get("addressLine")), like);
                var cityLike = cb.like(cb.lower(root.get("city")), like);
                p.getExpressions().add(cb.or(nameLike, addrLike, cityLike));
            }
            if (hasText(f.getCity())) {
                p.getExpressions().add(cb.equal(cb.lower(root.get("city")), f.getCity().toLowerCase(Locale.ROOT)));
            }
            if (hasText(f.getCountry())) {
                p.getExpressions().add(cb.equal(cb.lower(root.get("country")), f.getCountry().toLowerCase(Locale.ROOT)));
            }
            if (hasText(f.getPostalCode())) {
                p.getExpressions().add(cb.equal(cb.lower(root.get("postalCode")), f.getPostalCode().toLowerCase(Locale.ROOT)));
            }
            return p;
        };
    }

    private static boolean hasText(String s) {
        return s != null && !s.isBlank();
    }
}

