package com.vbt.logistics.bean.location;

import com.vbt.logistics.dto.LocationDto;
import com.vbt.logistics.entity.Location;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.LocationMapper;
import com.vbt.logistics.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetLocationBean {
    private final LocationRepository repo;
    private final LocationMapper mapper;

    @Transactional(readOnly = true)
    public LocationDto get(Long id) {
        Location e = repo.findById(id).orElseThrow(() -> new NotFoundException("Location not found: " + id));
        return mapper.map(e);
    }
}

