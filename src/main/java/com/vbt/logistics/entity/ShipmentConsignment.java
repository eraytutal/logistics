package com.vbt.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shipment_consignment", schema = "logistics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentConsignment {

    @EmbeddedId
    private ShipmentConsignmentId id;

    @MapsId("shipmentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @MapsId("consignmentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consignment_id", nullable = false)
    private Consignment consignment;
}

