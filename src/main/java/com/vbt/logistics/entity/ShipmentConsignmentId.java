package com.vbt.logistics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ShipmentConsignmentId implements Serializable {
    @Column(name = "shipment_id") private Long shipmentId;

    @Column(name = "consignment_id") private Long consignmentId;
}
