package com.vbt.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shipment", schema = "logistics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "created_at", nullable = false)
    private Instant createdAt;


    @Column(name = "reference_no", length = 80)
    private String referenceNo;


    @Builder.Default
    @OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
    private List<ShipmentConsignment> shipmentConsignment = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
    private List<ShipmentLeg> shipmentLeg = new ArrayList<>();
}
