package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.AttachConsignmentsRequestDto;
import com.vbt.logistics.dto.ShipmentConsignmentDto;
import com.vbt.logistics.entity.Consignment;
import com.vbt.logistics.entity.Shipment;
import com.vbt.logistics.entity.ShipmentConsignment;
import com.vbt.logistics.entity.ShipmentConsignmentId;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ConsignmentRepository;
import com.vbt.logistics.repository.ShipmentConsignmentRepository;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachConsignmentsBean {

    private final ShipmentRepository shipmentRepo;
    private final ConsignmentRepository consRepo;
    private final ShipmentConsignmentRepository scRepo;
    private final ShipmentMapper mapper;

    @Transactional
    public List<ShipmentConsignmentDto> attach(Long shipmentId, AttachConsignmentsRequestDto req) {
        Shipment shipment = shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment not found: " + shipmentId));

        // Boş/null liste -> 400
        if (req.consignmentIds() == null || req.consignmentIds().isEmpty()) {
            throw new IllegalArgumentException("consignmentIds must not be empty");
        }

        // Idempotent yaklaşım:
        // - varsa DTO olarak geri döner
        // - yoksa yeni ShipmentConsignment oluşturulur
        return req.consignmentIds().stream().map(cId -> {
            Consignment c = consRepo.findById(cId)
                    .orElseThrow(() -> new NotFoundException("Consignment not found: " + cId));

            boolean exists = scRepo.existsById_ShipmentIdAndId_ConsignmentId(shipment.getId(), c.getId());
            if (exists) {
                // var olan çifti "yok say" – DTO döndür (idempotent davranış)
                return new ShipmentConsignmentDto(shipment.getId(), c.getId());
            }

            ShipmentConsignment sc = ShipmentConsignment.builder()
                    .shipment(shipment)
                    .consignment(c)
                    .build();
            sc.setId(new ShipmentConsignmentId(shipment.getId(), c.getId()));

            sc = scRepo.save(sc);
            return mapper.mapShipmentConsignment(sc);
        }).toList();
    }
}
