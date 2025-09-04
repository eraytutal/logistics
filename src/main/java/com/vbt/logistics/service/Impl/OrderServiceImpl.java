package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.order.*;
import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CreateOrderBean createOrderBean;
    private final GetOrderBean getOrderBean;
    private final AddOrderItemBean addOrderItemBean;
    private final ListOrderItemsBean listOrderItemsBean;
    private final AddOrderStopBean addOrderStopBean;
    private final ListOrderStopsBean listOrderStopsBean;

    @Override @Transactional
    public OrderDto createOrder(CreateOrderRequestDto req) {
        return createOrderBean.create(req);
    }

    @Override @Transactional(readOnly = true)
    public OrderDto getOrder(Long id) {
        return getOrderBean.get(id);
    }

    @Override @Transactional
    public OrderItemDto addItem(Long orderId, AddOrderItemRequestDto req) {
        return addOrderItemBean.add(orderId, req);
    }

    @Override @Transactional(readOnly = true)
    public List<OrderItemDto> listItems(Long orderId) {
        return listOrderItemsBean.list(orderId);
    }

    @Override @Transactional
    public OrderStopDto addStop(Long orderId, AddOrderStopRequestDto req) {
        return addOrderStopBean.add(orderId, req);
    }

    @Override @Transactional(readOnly = true)
    public List<OrderStopDto> listStops(Long orderId) {
        return listOrderStopsBean.list(orderId);
    }
}
