package com.vbt.logistics.repository;

import com.vbt.logistics.entity.Carrier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    Page<Carrier> findByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);
}

