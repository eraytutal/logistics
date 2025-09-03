package com.vbt.logistics.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicle", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "plate_number", length = 20, unique = true)
    private String plateNumber;


    @Column(name = "capacity_kg", precision = 12, scale = 3)
    private BigDecimal capacityKg;


    @Column(name = "capacity_m3", precision = 12, scale = 3)
    private BigDecimal capacityM3;


    @Column(name = "pallet_cap")
    private Integer palletCap;


    @Column(name = "adr_compliant", nullable = false)
    private boolean adrCompliant;


    @Column(name = "cold_chain", nullable = false)
    private boolean coldChain;
}
