package com.vbt.logistics.bean.vehicle;

import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.VehicleDto;
import com.vbt.logistics.mapper.VehicleMapper;
import com.vbt.logistics.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListVehiclesBean {
    private final VehicleRepository repo;
    private final VehicleMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<VehicleDto> list(String q, Pageable pageable) {
        var page = (q == null || q.isBlank())
                ? repo.findAll(pageable)
                : repo.findByPlateNumberContainingIgnoreCase(q.trim(), pageable);
        return PageResponseDto.from(page.map(mapper::map));
    }
}

