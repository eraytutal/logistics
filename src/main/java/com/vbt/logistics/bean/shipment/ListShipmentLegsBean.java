package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.ShipmentLegDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentLegRepository;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListShipmentLegsBean {
    private final ShipmentRepository shipmentRepo;
    private final ShipmentLegRepository legRepo;
    private final ShipmentMapper mapper;

    @Transactional(readOnly = true)
    public List<ShipmentLegDto> list(Long shipmentId) {
        shipmentRepo.findById(shipmentId).orElseThrow(() -> new NotFoundException("Shipment not found: " + shipmentId));
        return legRepo.findByShipmentIdOrderBySeqAsc(shipmentId).stream()
                .map(mapper::mapLeg).toList();
    }
}
