package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.order.*;
import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CreateOrderBean createOrderBean;
    private final GetOrderBean getOrderBean;
    private final AddOrderItemBean addOrderItemBean;
    private final ListOrderItemsBean listOrderItemsBean;
    private final AddOrderStopBean addOrderStopBean;
    private final ListOrderStopsBean listOrderStopsBean;

    @Override
    @Transactional
    public OrderDto createOrder(CreateOrderRequestDto req) {
        return createOrderBean.create(req);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrder(Long id) {
        return getOrderBean.get(id);
    }

    @Override
    @Transactional
    public OrderItemDto addItem(Long orderId, AddOrderItemRequestDto req) {
        return addOrderItemBean.add(orderId, req);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<OrderItemDto> listItems(Long orderId, Pageable pageable) {
        return listOrderItemsBean.list(orderId, pageable);
    }

    @Override
    @Transactional
    public OrderStopDto addStop(Long orderId, AddOrderStopRequestDto req) {
        return addOrderStopBean.add(orderId, req);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<OrderStopDto> listStops(Long orderId, Pageable pageable) {
        return listOrderStopsBean.list(orderId, pageable);
    }
}