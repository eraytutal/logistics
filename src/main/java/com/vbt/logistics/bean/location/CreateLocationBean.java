package com.vbt.logistics.bean.location;

import com.vbt.logistics.dto.LocationDto;
import com.vbt.logistics.dto.CreateLocationRequestDto;
import com.vbt.logistics.entity.Location;
import com.vbt.logistics.mapper.LocationMapper;
import com.vbt.logistics.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateLocationBean {
    private final LocationRepository repo;
    private final LocationMapper mapper;

    @Transactional
    public LocationDto create(CreateLocationRequestDto req) {
        Location e = Location.builder()
                .name(req.name())
                .addressLine(req.addressLine())
                .city(req.city())
                .country(req.country())
                .postalCode(req.postalCode())
                .latitude(req.latitude())
                .longitude(req.longitude())
                .build();
        e = repo.save(e);
        return mapper.map(e);
    }
}

