package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.*;
import com.vbt.logistics.entity.Order;
import com.vbt.logistics.entity.OrderItem;
import com.vbt.logistics.entity.OrderParty;
import com.vbt.logistics.entity.OrderStop;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDto mapOrder(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCreatedAt(),
                order.getSpecialNotes()
        );
    }

    public OrderItemDto mapOrderItem(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getOrder() != null ? orderItem.getOrder().getId() : null,
                orderItem.getDescription(),
                orderItem.getWeightKg(),
                orderItem.getVolumeM3(),
                orderItem.isHazardFlag(),
                orderItem.isTempControl()
        );
    }

    public OrderStopDto mapOrderStop(OrderStop orderStop) {
        return new OrderStopDto(
                orderStop.getId(),
                orderStop.getOrder() != null ? orderStop.getOrder().getId() : null,
                orderStop.getRole(),
                orderStop.getLocation() != null ? orderStop.getLocation().getId() : null,
                orderStop.getWindowStart(),
                orderStop.getWindowEnd(),
                orderStop.getSeq()
        );
    }

    public OrderPartyDto mapOrderParty(OrderParty p) {
        return new OrderPartyDto(
                p.getId(),
                p.getOrder() != null ? p.getOrder().getId() : null,
                p.getRole(),
                p.getPartyName()
        );
    }
}


