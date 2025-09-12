package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.CarrierDto;
import com.vbt.logistics.entity.Carrier;
import org.springframework.stereotype.Component;

@Component
public class CarrierMapper {

    public CarrierDto map(Carrier c) {
        return new CarrierDto(
                c.getId(),
                c.getName(),
                c.getContact(),
                c.getLocation() != null ? c.getLocation().getId()   : null,
                c.getLocation() != null ? c.getLocation().getName() : null
        );
    }
}
