package com.vbt.logistics.repository;

import com.vbt.logistics.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
