package com.vbt.logistics.repository;

import com.vbt.logistics.entity.OrderStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderStopRepository extends JpaRepository<OrderStop, Long> {
    List<OrderStop> findByOrderIdOrderBySeqAsc(Long orderId);
}
