package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.DriverDto;
import com.vbt.logistics.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    public DriverDto map(Driver d) {
        return new DriverDto(
                d.getId(),
                d.getFullName(),
                d.getLicenseNo(),
                d.getPhone()
        );
    }
}
