package com.vbt.logistics.service;

import com.vbt.logistics.dto.*;
import java.util.List;

public interface OrderService {
    OrderDto createOrder(CreateOrderRequestDto req);
    OrderDto getOrder(Long id);

    OrderItemDto addItem(Long orderId, AddOrderItemRequestDto req);
    List<OrderItemDto> listItems(Long orderId);

    OrderStopDto addStop(Long orderId, AddOrderStopRequestDto req);
    List<OrderStopDto> listStops(Long orderId);
}

