package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.ShipmentLegDto;
import com.vbt.logistics.dto.ShipmentLegSearchParams;
import com.vbt.logistics.entity.ShipmentLeg;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.ShipmentLegRepository;
import com.vbt.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vbt.logistics.repository.specification.ShipmentLegSpecifications.*;

@Service
@RequiredArgsConstructor
public class SearchShipmentLegsBean {

    private final ShipmentRepository shipmentRepo;
    private final ShipmentLegRepository legRepo;
    private final ShipmentMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<ShipmentLegDto> search(Long shipmentId,
                                                  ShipmentLegSearchParams p,
                                                  Pageable pageable) {

        shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment not found: " + shipmentId));

        Specification<ShipmentLeg> spec = (root, cq, cb) -> cb.conjunction();

        spec = spec.and(hasShipmentId(shipmentId))
                .and(modeIs(p.mode()))
                .and(carrierIdIs(p.carrierId()))
                .and(vehicleIdIs(p.vehicleId()))
                .and(driverIdIs(p.driverId()))
                .and(startLocationIdIs(p.startLocationId()))
                .and(endLocationIdIs(p.endLocationId()))
                .and(plannedBetween(p.plannedFrom(), p.plannedTo()))
                .and(actualBetween(p.actualFrom(), p.actualTo()));

        Page<ShipmentLegDto> page = legRepo.findAll(spec, pageable)
                .map(mapper::mapLeg);

        return PageResponseDto.from(page);
    }
}
