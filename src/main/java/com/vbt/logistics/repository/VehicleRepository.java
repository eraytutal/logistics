package com.vbt.logistics.repository;

import com.vbt.logistics.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Page<Vehicle> findByPlateNumberContainingIgnoreCase(String q, Pageable pageable);
    boolean existsByPlateNumberIgnoreCase(String plateNumber);
}
