package com.vbt.logistics.bean.shipment;

import com.vbt.logistics.bean.statusEvent.StatusEventRecorder;
import com.vbt.logistics.dto.AddShipmentLegRequestDto;
import com.vbt.logistics.dto.ShipmentLegDto;
import com.vbt.logistics.entity.*;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ShipmentMapper;
import com.vbt.logistics.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vbt.logistics.util.StatusCodes.LEG_ADDED;

@Service
@RequiredArgsConstructor
public class AddShipmentLegBean {
    private final ShipmentRepository shipmentRepo;
    private final ShipmentLegRepository legRepo;
    private final VehicleRepository vehicleRepo;
    private final DriverRepository driverRepo;
    private final CarrierRepository carrierRepo;
    private final com.vbt.logistics.repository.LocationRepository locationRepo;
    private final ShipmentMapper mapper;
    private final StatusEventRecorder eventRecorder;

    @Transactional
    public ShipmentLegDto add(Long shipmentId, AddShipmentLegRequestDto req) {
        Shipment shipment = shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment not found: " + shipmentId));

        if (legRepo.existsByShipmentIdAndSeq(shipmentId, req.seq())) {
            throw new IllegalArgumentException("seq already exists for this shipment");
        }

        Location start = locationRepo.findById(req.startLocationId())
                .orElseThrow(() -> new NotFoundException("Start location not found: " + req.startLocationId()));
        Location end = locationRepo.findById(req.endLocationId())
                .orElseThrow(() -> new NotFoundException("End location not found: " + req.endLocationId()));

        Vehicle vehicle = null;
        Driver driver = null;
        Carrier carrier = null;
        if (req.vehicleId() != null) {
            vehicle = vehicleRepo.findById(req.vehicleId())
                    .orElseThrow(() -> new NotFoundException("Vehicle not found: " + req.vehicleId()));
        }
        if (req.driverId() != null) {
            driver = driverRepo.findById(req.driverId())
                    .orElseThrow(() -> new NotFoundException("Driver not found: " + req.driverId()));
        }
        if (req.carrierId() != null) {
            carrier = carrierRepo.findById(req.carrierId())
                    .orElseThrow(() -> new NotFoundException("Carrier not found: " + req.carrierId()));
        }

        // Basit mod/alan tutarlılığı (gerekirse sıkılaştırılabilir)
        switch (req.mode()) {
            case ROAD -> {
                // Kendi filomuz: en azından vehicle veya driver’dan biri dolu olsun dedik.
                if (vehicle == null && driver == null && carrier == null) {
                    throw new IllegalArgumentException("ROAD leg requires vehicle/driver or external carrier");
                }
            }
            case AIR, SEA -> {
                if (carrier == null) throw new IllegalArgumentException(req.mode() + " leg requires carrier");
            }
        }

        ShipmentLeg savedLeg = ShipmentLeg.builder()
                .shipment(shipment)
                .seq(req.seq())
                .mode(req.mode())
                .vehicle(vehicle)
                .driver(driver)
                .carrier(carrier)
                .bookingRef(req.bookingRef())
                .awbNo(req.awbNo())
                .blNo(req.blNo())
                .cmrNo(req.cmrNo())
                .startLocation(start)
                .endLocation(end)
                .plannedStart(req.plannedStart())
                .plannedEnd(req.plannedEnd())
                .build();

        savedLeg = legRepo.save(savedLeg);

        eventRecorder.record(EntityType.SHIPMENT,
                shipment.getId(), LEG_ADDED,
                "legId=" + savedLeg.getId() + ", seq=" + savedLeg.getSeq());

        return mapper.mapLeg(savedLeg);
    }
}

