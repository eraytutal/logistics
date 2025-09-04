package com.vbt.logistics.bean.order;

import com.vbt.logistics.dto.OrderDto;
import com.vbt.logistics.entity.Order;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetOrderBean {
    private final OrderRepository repo;
    private final OrderMapper mapper;

    @Transactional(readOnly = true)
    public OrderDto get(Long id) {
        Order order = repo.findById(id).orElseThrow(() -> new NotFoundException("Order not found: " + id));
        return mapper.mapOrder(order);
    }
}
