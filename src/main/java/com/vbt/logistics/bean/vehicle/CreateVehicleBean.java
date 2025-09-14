package com.vbt.logistics.bean.vehicle;

import com.vbt.logistics.dto.CreateVehicleRequestDto;
import com.vbt.logistics.dto.VehicleDto;
import com.vbt.logistics.entity.Vehicle;
import com.vbt.logistics.mapper.VehicleMapper;
import com.vbt.logistics.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateVehicleBean {
    private final VehicleRepository repo;
    private final VehicleMapper mapper;

    @Transactional
    public VehicleDto create(CreateVehicleRequestDto req) {
        if (repo.existsByPlateNumberIgnoreCase(req.plateNumber())) {
            throw new IllegalArgumentException("Plate already exists: " + req.plateNumber());
        }
        var v = Vehicle.builder()
                .plateNumber(req.plateNumber())
                .capacityKg(BigDecimal.valueOf(req.capacityKg()))
                .capacityM3(BigDecimal.valueOf(req.capacityM3()))
                .palletCap(req.palletCap())
                .adrCompliant(Boolean.TRUE.equals(req.adrCompliant()))
                .coldChain(Boolean.TRUE.equals(req.coldChain()))
                .build();
        return mapper.map(repo.save(v));
    }
}

