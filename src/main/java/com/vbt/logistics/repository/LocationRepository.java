package com.vbt.logistics.repository;

import com.vbt.logistics.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LocationRepository
        extends JpaRepository<Location, Long>, JpaSpecificationExecutor<Location> {

}
