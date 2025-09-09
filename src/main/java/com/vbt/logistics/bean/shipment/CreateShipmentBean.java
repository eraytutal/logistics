package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.bean.statusEvent.StatusEventRecorder;
import com.vbt.logistics.dto.CreateShipmentRequestDto;
import com.vbt.logistics.dto.ShipmentDto;
import com.vbt.logistics.entity.Shipment;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vbt.logistics.util.StatusCodes.SHIPMENT_CREATED;


@Service
@RequiredArgsConstructor
public class CreateShipmentBean {
    private final ShipmentRepository repo;
    private final ShipmentMapper mapper;
    private final StatusEventRecorder eventRecorder;

    @Transactional
    public ShipmentDto create(CreateShipmentRequestDto req) {
        Shipment savedShipment = Shipment.builder()
                .referenceNo(req.referenceNo())
                .build();
        savedShipment = repo.save(savedShipment);

        eventRecorder.record(EntityType.SHIPMENT,
                savedShipment.getId(), SHIPMENT_CREATED,
                "reference=" + savedShipment.getReferenceNo());

        return mapper.mapShipment(savedShipment);
    }

}
