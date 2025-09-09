package com.vbt.logistics.bean.order;

import com.vbt.logistics.bean.statusEvent.StatusEventRecorder;
import com.vbt.logistics.dto.AddOrderStopRequestDto;
import com.vbt.logistics.dto.OrderStopDto;
import com.vbt.logistics.entity.Location;
import com.vbt.logistics.entity.Order;
import com.vbt.logistics.entity.OrderStop;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.LocationRepository;
import com.vbt.logistics.repository.OrderRepository;
import com.vbt.logistics.repository.OrderStopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vbt.logistics.util.StatusCodes.ORDER_STOP_ADDED;

@Service
@RequiredArgsConstructor
public class AddOrderStopBean {
    private final OrderRepository orderRepo;
    private final LocationRepository locationRepo;
    private final OrderStopRepository stopRepo;
    private final OrderMapper mapper;
    private final StatusEventRecorder eventRecorder;

    @Transactional
    public OrderStopDto add(Long orderId, AddOrderStopRequestDto req) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found: " + orderId));
        Location loc = locationRepo.findById(req.locationId())
                .orElseThrow(() -> new NotFoundException("Location not found: " + req.locationId()));

        OrderStop orderStop = OrderStop.builder()
                .order(order)
                .role(req.role())
                .location(loc)
                .windowStart(req.windowStart())
                .windowEnd(req.windowEnd())
                .seq(req.seq())
                .build();

        orderStop = stopRepo.save(orderStop);

        eventRecorder.record(EntityType.ORDER,
                order.getId(), ORDER_STOP_ADDED,
                "stopId=" + orderStop.getId() + ", role=" + orderStop.getRole());

        return mapper.mapOrderStop(orderStop);
    }
}

