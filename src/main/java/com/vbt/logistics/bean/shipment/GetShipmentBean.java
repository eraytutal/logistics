package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.ShipmentDto;
import com.vbt.logistics.entity.Shipment;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetShipmentBean {
    private final ShipmentRepository repo;
    private final ShipmentMapper mapper;

    @Transactional(readOnly = true)
    public ShipmentDto get(Long id) {
        Shipment s = repo.findById(id).orElseThrow(() -> new NotFoundException("Shipment not found: " + id));
        return mapper.mapShipment(s);
    }
}
