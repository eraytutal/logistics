package com.vbt.logistics.bean.vehicle;

import com.vbt.logistics.dto.VehicleDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.VehicleMapper;
import com.vbt.logistics.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class GetVehicleBean {
    private final VehicleRepository repo;
    private final VehicleMapper mapper;

    @Transactional(readOnly = true)
    public VehicleDto get(Long id) {
        var v = repo.findById(id).orElseThrow(() -> new NotFoundException("Vehicle not found: " + id));
        return mapper.map(v);
    }
}

