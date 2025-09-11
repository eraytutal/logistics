package com.vbt.logistics.dto;

import lombok.Data;

@Data
public class LocationFilterDto {
    private String q;
    private String city;
    private String country;
    private String postalCode;
}
