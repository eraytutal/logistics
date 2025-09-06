package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.CreateShipmentRequestDto;
import com.vbt.logistics.dto.ShipmentDto;
import com.vbt.logistics.entity.Shipment;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CreateShipmentBean {
    private final ShipmentRepository repo;
    private final ShipmentMapper mapper;

    @Transactional
    public ShipmentDto create(CreateShipmentRequestDto req) {
        Shipment savedShipment = Shipment.builder()
                .referenceNo(req.referenceNo())
                .build();
        savedShipment = repo.save(savedShipment);
        return mapper.mapShipment(savedShipment);
    }

}
