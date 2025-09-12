package com.vbt.logistics.bean.carrier;

import com.vbt.logistics.dto.CarrierDto;
import com.vbt.logistics.dto.UpdateCarrierRequestDto;
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
public class UpdateCarrierBean {

    private final CarrierRepository carrierRepo;
    private final LocationRepository locationRepo;
    private final CarrierMapper mapper;

    @Transactional
    public CarrierDto update(Long id, UpdateCarrierRequestDto req) {
        var entity = carrierRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Carrier not found: " + id));

        Location loc = null;
        if (req.locationId() != null) {
            loc = locationRepo.findById(req.locationId())
                    .orElseThrow(() -> new NotFoundException("Location not found: " + req.locationId()));
        }

        entity.setName(req.name());
        entity.setContact(req.contact());
        entity.setLocation(loc);

        return mapper.map(entity);
    }
}
