package com.vbt.logistics.bean.carrier;

import com.vbt.logistics.dto.CarrierDto;
import com.vbt.logistics.dto.CreateCarrierRequestDto;
import com.vbt.logistics.entity.Carrier;
import com.vbt.logistics.entity.Location;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.CarrierMapper;
import com.vbt.logistics.repository.CarrierRepository;
import com.vbt.logistics.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCarrierBean {

    private final CarrierRepository carrierRepo;
    private final LocationRepository locationRepo;
    private final CarrierMapper mapper;

    @Transactional
    public CarrierDto create(CreateCarrierRequestDto req) {
        if (carrierRepo.existsByNameIgnoreCase(req.name())) {
            throw new IllegalArgumentException("Carrier name already exists: " + req.name());
        }

        Location loc = null;
        if (req.locationId() != null) {
            loc = locationRepo.findById(req.locationId())
                    .orElseThrow(() -> new NotFoundException("Location not found: " + req.locationId()));
        }

        Carrier c = Carrier.builder()
                .name(req.name())
                .contact(req.contact())
                .location(loc)
                .build();

        return mapper.map(carrierRepo.save(c));
    }
}

