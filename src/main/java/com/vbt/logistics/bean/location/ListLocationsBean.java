package com.vbt.logistics.bean.location;

import com.vbt.logistics.dto.LocationDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.LocationFilterDto;
import com.vbt.logistics.entity.Location;
import com.vbt.logistics.mapper.LocationMapper;
import com.vbt.logistics.repository.LocationRepository;
import com.vbt.logistics.repository.specification.LocationSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListLocationsBean {
    private final LocationRepository repo;
    private final LocationMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<LocationDto> list(LocationFilterDto filter, Pageable pageable) {
        Specification<Location> spec = LocationSpecifications.byFilter(filter);
        Page<LocationDto> page = repo.findAll(spec, pageable)
                .map(mapper::map);
        return PageResponseDto.from(page);
    }
}

