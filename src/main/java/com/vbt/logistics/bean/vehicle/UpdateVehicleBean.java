package com.vbt.logistics.bean.vehicle;

import com.vbt.logistics.dto.UpdateVehicleRequestDto;
import com.vbt.logistics.dto.VehicleDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.VehicleMapper;
import com.vbt.logistics.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UpdateVehicleBean {
    private final VehicleRepository repo;
    private final VehicleMapper mapper;

    @Transactional
    public VehicleDto update(Long id, UpdateVehicleRequestDto req) {
        var v = repo.findById(id).orElseThrow(() -> new NotFoundException("Vehicle not found: " + id));
        v.setPlateNumber(req.plateNumber());
        v.setCapacityKg(BigDecimal.valueOf(req.capacityKg()));
        v.setCapacityM3(BigDecimal.valueOf(req.capacityM3()));
        v.setPalletCap(req.palletCap());
        v.setAdrCompliant(Boolean.TRUE.equals(req.adrCompliant()));
        v.setColdChain(Boolean.TRUE.equals(req.coldChain()));
        return mapper.map(v);
    }
}

