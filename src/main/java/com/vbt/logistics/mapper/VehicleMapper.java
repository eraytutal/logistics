package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.VehicleDto;
import com.vbt.logistics.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    public VehicleDto map(Vehicle v) {
        return new VehicleDto(
                v.getId(),
                v.getPlateNumber(),
                v.getCapacityKg(),
                v.getCapacityM3(),
                v.getPalletCap(),
                v.isAdrCompliant(),
                v.isAdrCompliant()
        );
    }
}

