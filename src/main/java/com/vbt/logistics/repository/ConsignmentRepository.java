package com.vbt.logistics.repository;

import com.vbt.logistics.entity.Consignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsignmentRepository extends JpaRepository<Consignment, Long> {
}
