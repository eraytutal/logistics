package com.vbt.logistics.bean.order;

import com.vbt.logistics.dto.OrderStopDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderRepository;
import com.vbt.logistics.repository.OrderStopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListOrderStopsBean {
    private final OrderRepository orderRepo;
    private final OrderStopRepository stopRepo;
    private final OrderMapper mapper;

    @Transactional(readOnly = true)
    public List<OrderStopDto> list(Long orderId) {
        orderRepo.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found: " + orderId));
        return stopRepo.findByOrderIdOrderBySeqAsc(orderId).stream().map(mapper::mapOrderStop).toList();
    }
}

