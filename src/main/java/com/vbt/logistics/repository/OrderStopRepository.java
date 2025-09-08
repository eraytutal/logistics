package com.vbt.logistics.repository;

import com.vbt.logistics.entity.OrderStop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderStopRepository extends JpaRepository<OrderStop, Long> {
    // Liste uçlarında location ismi/adresi göstermek istediğinde N+1’i önler
    @EntityGraph(attributePaths = {"location"})
    Page<OrderStop> findByOrderId(Long orderId, Pageable pageable);
}
