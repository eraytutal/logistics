package com.vbt.logistics.bean.order;

import com.vbt.logistics.dto.OrderItemDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderItemRepository;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListOrderItemsBean {
    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final OrderMapper mapper;

    @Transactional(readOnly = true)
    public List<OrderItemDto> list(Long orderId) {
        orderRepo.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found: " + orderId));
        return itemRepo.findByOrderId(orderId).stream().map(mapper::mapOrderItem).toList();
    }
}

