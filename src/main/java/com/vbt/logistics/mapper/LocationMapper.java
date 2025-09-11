package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.LocationDto;
import com.vbt.logistics.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public LocationDto map(Location e) {
        return new LocationDto(
                e.getId(),
                e.getName(),
                e.getAddressLine(),
                e.getCity(),
                e.getCountry(),
                e.getPostalCode(),
                e.getLatitude(),
                e.getLongitude()
        );
    }
}
