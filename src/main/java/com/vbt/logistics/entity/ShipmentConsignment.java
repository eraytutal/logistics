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


    @ManyToOne(fetch = FetchType.LAZY) @MapsId("shipmentId")
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;


    @ManyToOne(fetch = FetchType.LAZY) @MapsId("consignmentId")
    @JoinColumn(name = "consignment_id", nullable = false)
    private Consignment consignment;
}
