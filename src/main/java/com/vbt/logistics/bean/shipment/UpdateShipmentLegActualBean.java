package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.bean.statusEvent.StatusEventRecorder;
import com.vbt.logistics.dto.ShipmentLegDto;
import com.vbt.logistics.dto.UpdateShipmentLegActualRequestDto;
import com.vbt.logistics.entity.ShipmentLeg;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentLegRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vbt.logistics.util.StatusCodes.LEG_ACTUAL_UPDATED;

@Service
@RequiredArgsConstructor
public class UpdateShipmentLegActualBean {
    private final ShipmentLegRepository legRepo;
    private final ShipmentMapper mapper;
    private final StatusEventRecorder eventRecorder;

    @Transactional
    public ShipmentLegDto update(Long legId, UpdateShipmentLegActualRequestDto req) {
        ShipmentLeg leg = legRepo.findById(legId)
                .orElseThrow(() -> new NotFoundException("ShipmentLeg not found: " + legId));

        if (req.actualStart() != null) leg.setActualStart(req.actualStart());
        if (req.actualEnd() != null) leg.setActualEnd(req.actualEnd());

        leg = legRepo.save(leg);

        eventRecorder.record(EntityType.LEG,
                leg.getId(), LEG_ACTUAL_UPDATED,
                "start=" + req.actualStart() + ", end=" + req.actualEnd());

        return mapper.mapLeg(leg);
    }
}
