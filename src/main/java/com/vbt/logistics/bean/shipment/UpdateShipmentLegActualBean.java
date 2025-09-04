package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.ShipmentLegDto;
import com.vbt.logistics.dto.UpdateShipmentLegActualRequestDto;
import com.vbt.logistics.entity.ShipmentLeg;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentLegRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateShipmentLegActualBean {
    private final ShipmentLegRepository legRepo;
    private final ShipmentMapper mapper;

    @Transactional
    public ShipmentLegDto update(Long legId, UpdateShipmentLegActualRequestDto req) {
        ShipmentLeg leg = legRepo.findById(legId)
                .orElseThrow(() -> new NotFoundException("ShipmentLeg not found: " + legId));

        if (req.actualStart() != null) leg.setActualStart(req.actualStart());
        if (req.actualEnd()   != null) leg.setActualEnd(req.actualEnd());

        leg = legRepo.save(leg);
        return mapper.mapLeg(leg);
    }
}
