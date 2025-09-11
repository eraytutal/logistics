package com.vbt.logistics.bean.location;

import com.vbt.logistics.dto.LocationDto;
import com.vbt.logistics.dto.UpdateLocationRequestDto;
import com.vbt.logistics.entity.Location;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.LocationMapper;
import com.vbt.logistics.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateLocationBean {
    private final LocationRepository repo;
    private final LocationMapper mapper;

    @Transactional
    public LocationDto update(Long id, UpdateLocationRequestDto req) {
        Location e = repo.findById(id).orElseThrow(() -> new NotFoundException("Location not found: " + id));

        if (req.name() != null) e.setName(req.name());
        if (req.addressLine() != null) e.setAddressLine(req.addressLine());
        if (req.city() != null) e.setCity(req.city());
        if (req.country() != null) e.setCountry(req.country());
        if (req.postalCode() != null) e.setPostalCode(req.postalCode());
        if (req.latitude() != null) e.setLatitude(req.latitude());
        if (req.longitude() != null) e.setLongitude(req.longitude());

        e = repo.save(e);
        return mapper.map(e);
    }
}

