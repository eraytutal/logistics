package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.ShipmentConsignmentDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentConsignmentRepository;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListShipmentConsignmentsBean {
    private final ShipmentRepository shipmentRepo;
    private final ShipmentConsignmentRepository scRepo;
    private final ShipmentMapper mapper;


    @Transactional(readOnly = true)
    public PageResponseDto<ShipmentConsignmentDto> list(Long shipmentId, Pageable pageable) {
        shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment not found: " + shipmentId));

        Page<ShipmentConsignmentDto> page =
                scRepo.findById_ShipmentId(shipmentId, pageable)
                        .map(mapper::mapShipmentConsignment);

        return PageResponseDto.from(page);
    }
}
