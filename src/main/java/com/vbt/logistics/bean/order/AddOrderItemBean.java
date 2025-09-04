package com.vbt.logistics.bean.order;

// bean/AddOrderItemBean.java

import com.vbt.logistics.dto.AddOrderItemRequestDto;
import com.vbt.logistics.dto.OrderItemDto;
import com.vbt.logistics.entity.Order;
import com.vbt.logistics.entity.OrderItem;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderItemRepository;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddOrderItemBean {
    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final OrderMapper mapper;

    @Transactional
    public OrderItemDto add(Long orderId, AddOrderItemRequestDto req) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found: " + orderId));

        OrderItem item = OrderItem.builder()
                .order(order)
                .description(req.description())
                .weightKg(req.weightKg())
                .volumeM3(req.volumeM3())
                .hazardFlag(req.hazardFlag())
                .tempControl(req.tempControl())
                .build();

        item = itemRepo.save(item);
        return mapper.mapOrderItem(item);
    }
}

