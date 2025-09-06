package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.ShipmentLegDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentLegRepository;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListShipmentLegsBean {
    private final ShipmentRepository shipmentRepo;
    private final ShipmentLegRepository legRepo;
    private final ShipmentMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<ShipmentLegDto> list(Long shipmentId, Pageable pageable) {
        shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment not found: " + shipmentId));

        Page<ShipmentLegDto> page = legRepo.findByShipmentId(shipmentId, pageable)
                .map(mapper::mapLeg);

        return PageResponseDto.from(page);
    }
}
