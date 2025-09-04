package com.vbt.logistics.bean.order;

import com.vbt.logistics.dto.CreateOrderRequestDto;
import com.vbt.logistics.dto.OrderDto;
import com.vbt.logistics.entity.Order;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CreateOrderBean {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    @Transactional
    public OrderDto create(CreateOrderRequestDto req) {
        Instant created = req.createdAt() != null ? req.createdAt() : Instant.now();
        Order savedOrder = Order.builder()
                .createdAt(created)
                .specialNotes(req.specialNotes())
                .build();
        savedOrder = orderRepository.save(savedOrder);
        return mapper.mapOrder(savedOrder);
    }
}
