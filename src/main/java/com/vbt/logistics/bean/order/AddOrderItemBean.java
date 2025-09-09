package com.vbt.logistics.bean.order;

// bean/AddOrderItemBean.java

import com.vbt.logistics.bean.statusEvent.StatusEventRecorder;
import com.vbt.logistics.dto.AddOrderItemRequestDto;
import com.vbt.logistics.dto.OrderItemDto;
import com.vbt.logistics.entity.Order;
import com.vbt.logistics.entity.OrderItem;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderItemRepository;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vbt.logistics.util.StatusCodes.ORDER_ITEM_ADDED;

@Service
@RequiredArgsConstructor
public class AddOrderItemBean {
    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final OrderMapper mapper;
    private final StatusEventRecorder eventRecorder;

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

        eventRecorder.record(EntityType.ORDER,
                order.getId(), ORDER_ITEM_ADDED, "itemId=" + item.getId());

        return mapper.mapOrderItem(item);
    }
}

