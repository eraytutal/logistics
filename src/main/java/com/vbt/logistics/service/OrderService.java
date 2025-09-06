package com.vbt.logistics.service;

import com.vbt.logistics.dto.*;
import org.springframework.data.domain.Pageable;


public interface OrderService {
    OrderDto createOrder(CreateOrderRequestDto req);
    OrderDto getOrder(Long id);

    OrderItemDto addItem(Long orderId, AddOrderItemRequestDto req);
    PageResponseDto<OrderItemDto> listItems(Long orderId, Pageable pageable);

    OrderStopDto addStop(Long orderId, AddOrderStopRequestDto req);
    PageResponseDto<OrderStopDto> listStops(Long orderId, Pageable pageable);
}

