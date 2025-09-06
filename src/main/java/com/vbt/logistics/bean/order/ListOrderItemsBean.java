package com.vbt.logistics.bean.order;

import com.vbt.logistics.dto.OrderItemDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderItemRepository;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ListOrderItemsBean {
    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final OrderMapper mapper;


    @Transactional(readOnly = true)
    public PageResponseDto<OrderItemDto> list(Long orderId, Pageable pageable) {
        orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found: " + orderId));
        Page<OrderItemDto> page = itemRepo.findByOrderId(orderId, pageable).map(mapper::mapOrderItem);
        return PageResponseDto.from(page);
    }
}

