package com.vbt.logistics.bean.order;

import com.vbt.logistics.bean.statusEvent.StatusEventRecorder;
import com.vbt.logistics.dto.CreateOrderRequestDto;
import com.vbt.logistics.dto.OrderDto;
import com.vbt.logistics.entity.Order;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vbt.logistics.util.StatusCodes.ORDER_CREATED;

@Service
@RequiredArgsConstructor
public class CreateOrderBean {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final StatusEventRecorder eventRecorder;

    @Transactional
    public OrderDto create(CreateOrderRequestDto req) {
        Order savedOrder = Order.builder()
                .specialNotes(req.specialNotes())
                .build();
        savedOrder = orderRepository.save(savedOrder);

        eventRecorder.record(EntityType.ORDER,
                savedOrder.getId(), ORDER_CREATED, null);

        return mapper.mapOrder(savedOrder);
    }
}
