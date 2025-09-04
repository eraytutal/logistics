package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.ShipmentConsignmentDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentConsignmentRepository;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListShipmentConsignmentsBean {
    private final ShipmentRepository shipmentRepo;
    private final ShipmentConsignmentRepository scRepo;
    private final ShipmentMapper mapper;

    @Transactional(readOnly = true)
    public List<ShipmentConsignmentDto> list(Long shipmentId) {
        shipmentRepo.findById(shipmentId).orElseThrow(() -> new NotFoundException("Shipment not found: " + shipmentId));
        return scRepo.findByShipmentId(shipmentId).stream()
                .map(mapper::mapShipmentConsignment).toList();
    }
}
