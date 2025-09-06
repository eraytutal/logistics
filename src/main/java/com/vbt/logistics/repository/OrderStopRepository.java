package com.vbt.logistics.repository;

import com.vbt.logistics.entity.OrderStop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderStopRepository extends JpaRepository<OrderStop, Long> {
    Page<OrderStop> findByOrderId(Long orderId, Pageable pageable);
}
