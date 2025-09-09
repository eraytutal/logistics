package com.vbt.logistics.bean.order;

import com.vbt.logistics.dto.OrderPartyDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.OrderMapper;
import com.vbt.logistics.repository.OrderPartyRepository;
import com.vbt.logistics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListOrderPartiesBean {
    private final OrderRepository orderRepo;
    private final OrderPartyRepository partyRepo;
    private final OrderMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<OrderPartyDto> list(Long orderId, Pageable pageable) {
        orderRepo.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found: " + orderId));
        var page = partyRepo.findByOrderId(orderId, pageable).map(mapper::mapOrderParty);
        return PageResponseDto.from(page);
    }
}
