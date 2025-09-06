package com.vbt.logistics.bean.order;

import com.vbt.logistics.dto.CreateOrderRequestDto;
import com.vbt.logistics.dto.OrderDto;
import com.vbt.logistics.entity.Order;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderBean {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    @Transactional
    public OrderDto create(CreateOrderRequestDto req) {
        Order savedOrder = Order.builder()
                .specialNotes(req.specialNotes())
                .build();
        savedOrder = orderRepository.save(savedOrder);
        return mapper.mapOrder(savedOrder);
    }
}
