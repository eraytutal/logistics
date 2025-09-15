package com.vbt.logistics.bean.consignment;

import com.vbt.logistics.dto.AddConsignmentItemRequestDto;
import com.vbt.logistics.dto.ConsignmentItemDto;
import com.vbt.logistics.entity.*;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ConsignmentMapper;
import com.vbt.logistics.repository.ConsignmentItemRepository;
import com.vbt.logistics.repository.ConsignmentRepository;
import com.vbt.logistics.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddConsignmentItemBean {

    private final ConsignmentRepository consRepo;
    private final OrderItemRepository orderItemRepo;
    private final ConsignmentItemRepository itemRepo;
    private final ConsignmentMapper mapper;

    @Transactional
    public ConsignmentItemDto add(Long consignmentId, AddConsignmentItemRequestDto req) {
        Consignment cons = consRepo.findById(consignmentId)
                .orElseThrow(() -> new NotFoundException("Consignment not found: " + consignmentId));
        OrderItem oi = orderItemRepo.findById(req.orderItemId())
                .orElseThrow(() -> new NotFoundException("OrderItem not found: " + req.orderItemId()));

        ConsignmentItemId id = new ConsignmentItemId(consignmentId, oi.getId());

        var existing = itemRepo.findById(id);
        if (existing.isPresent()) return mapper.mapItem(existing.get());

        ConsignmentItem ci = ConsignmentItem.builder()
                .id(id)
                .consignment(cons)
                .orderItem(oi)
                .weightKg(req.weightKg())
                .volumeM3(req.volumeM3())
                .build();

        return mapper.mapItem(itemRepo.save(ci));
    }
}
