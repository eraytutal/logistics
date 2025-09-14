package com.vbt.logistics.repository;

import com.vbt.logistics.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    Page<Driver> findByFullNameContainingIgnoreCaseOrLicenseNoContainingIgnoreCase(String name, String lic, Pageable pageable);
    boolean existsByLicenseNoIgnoreCase(String licenseNo);
}
